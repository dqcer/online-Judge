package com.jinhaoplus.oj.common;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.util.PropertiesUtil;

@Component
public class LangCoreResolver {
	@Autowired
	private ProblemsDao problemsDao;

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}
	
	@Autowired
	private SessionManager sessionManager;
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	
	
	public CommonMessage compileCode(String compileDir,String[] compileCommands){
		CommonMessage message = null;
		ProcessBuilder processBuilder;
		processBuilder = new ProcessBuilder(compileCommands);
		processBuilder.directory(new File(compileDir));
		processBuilder.redirectErrorStream(true);

		try {
			Process compileProcess = processBuilder.start();
			final InputStream inputStream = compileProcess.getInputStream();
			final InputStream errorStream = compileProcess.getErrorStream();

			ResultReadCallable compileResultThread = new ResultReadCallable(inputStream);
			ResultReadCallable compileErrorThread = new ResultReadCallable(errorStream);
			Future<String> compileErrorInfo = executor.submit(compileErrorThread);
			Future<String> compileResultInfo = executor.submit(compileResultThread);
			compileProcess.waitFor();
			compileProcess.destroy();
			
			if(compileProcess.exitValue()==0){
				message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE"), 
						PropertiesUtil.getProperty("COMPILE_SUCCESS"), 
						compileResultInfo.get());
			}else{
				message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_ERROR_CODE"), 
						PropertiesUtil.getProperty("COMPILE_ERROR"), 
						compileResultInfo.get());
			}
			return message;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProblemTestResult> ojRunCode(int problemId, int solutionId,String runDir,String[] runCommands){
		List<ProblemTest> problemTests = problemsDao
				.getTestsByProblemId(problemId);
		List<ProblemTestResult> results = new ArrayList<ProblemTestResult>();
		for (ProblemTest problemTest : problemTests) {
			CommonMessage message = null;
			ProcessBuilder processBuilder;
			processBuilder = new ProcessBuilder(runCommands);
			processBuilder.directory(new File(runDir));
			processBuilder.redirectErrorStream(true);

			
			try {
				Process runProcess = processBuilder.start();
				
				final InputStream inputStream = runProcess.getInputStream();
				final InputStream errorStream = runProcess.getErrorStream();
				final OutputStream outputStream = runProcess.getOutputStream();

				ResultReadCallable runResultThread = new ResultReadCallable(inputStream);
				ResultReadCallable runErrorThread = new ResultReadCallable(errorStream);
				TestWriteCallable runTestWriteThread = new TestWriteCallable(outputStream, problemTest.getProblemTestInput());
				Future<String> runErrorInfo = executor.submit(runErrorThread);
				Future<String> runResultInfo = executor.submit(runResultThread);
				executor.submit(runTestWriteThread);
				runProcess.waitFor();
				runProcess.destroy();
				
				if(runProcess.exitValue()==0){
					message = new CommonMessage(PropertiesUtil.getProperty("RUN_SUCCESS_CODE"), 
							PropertiesUtil.getProperty("RUN_SUCCESS"), 
							runResultInfo.get());
					ProblemTestResult testResult = new ProblemTestResult(problemId, problemTest.getProblemTestId(), runResultInfo.get(), "", message);
					testResult.setSolutionId(solutionId);
					String OJResult = this.OJResult(problemTest,testResult);
					testResult.setOjResult(OJResult);
					problemsDao.insertTestResult(testResult);
					testResult.setTestInput(problemTest.getProblemTestInput());
					testResult.setTestOutput(problemTest.getProblemTestOutput());
					results.add(testResult);
				}else{
					message = new CommonMessage(PropertiesUtil.getProperty("RUN_ERROR_CODE"), 
							PropertiesUtil.getProperty("RUN_ERROR"), 
							runResultInfo.get());
					ProblemTestResult testResult = new ProblemTestResult(problemId, problemTest.getProblemTestId(), runResultInfo.get(), "", message);
					testResult.setSolutionId(solutionId);
					String OJResult = this.OJResult(problemTest,testResult);
					testResult.setOjResult(OJResult);
					problemsDao.insertTestResult(testResult);
					testResult.setTestInput(problemTest.getProblemTestInput());
					testResult.setTestOutput(problemTest.getProblemTestOutput());
					results.add(testResult);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return results;
	}
	
	public ProblemTestResult cloudRunCode(String runDir,String[] runCommands,String cloudRunnnerSyncCode){
		CommonMessage message = null;
		ProcessBuilder processBuilder;
		processBuilder = new ProcessBuilder(runCommands);
		processBuilder.directory(new File(runDir));
		processBuilder.redirectErrorStream(true);
		try {
			Process runProcess = processBuilder.start();
			sessionManager.syncedProcessToSession(cloudRunnnerSyncCode,runProcess);
			final InputStream inputStream = runProcess.getInputStream();
			final InputStream errorStream = runProcess.getErrorStream();
			
			ResultReadCallable runResultThread = new ResultReadCallable(inputStream);
			ResultReadCallable runErrorThread = new ResultReadCallable(errorStream);
			Future<String> runErrorInfo = executor.submit(runErrorThread);
			Future<String> runResultInfo = executor.submit(runResultThread);
			runProcess.waitFor();
			runProcess.destroy();
			
			if(runProcess.exitValue()==0){
				message = new CommonMessage(PropertiesUtil.getProperty("RUN_SUCCESS_CODE"), 
						PropertiesUtil.getProperty("RUN_SUCCESS"), 
						runResultInfo.get());
				ProblemTestResult testResult = new ProblemTestResult();
				testResult.setResult(runResultInfo.get());
				testResult.setMessage(message);
				return testResult;
			}else{
				message = new CommonMessage(PropertiesUtil.getProperty("RUN_ERROR_CODE"), 
						PropertiesUtil.getProperty("RUN_ERROR"), 
						runResultInfo.get());
				ProblemTestResult testResult = new ProblemTestResult();
				testResult.setResult(runResultInfo.get());
				testResult.setMessage(message);
				return testResult;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public void cloudRunInput(String typedInput, String cloudRunnerSyncCode) {
		Process runProcess = (Process) sessionManager.getSession().getAttribute(cloudRunnerSyncCode);
		OutputStream outputStream = runProcess.getOutputStream();
		TestWriteCallable inputWriteThread = new TestWriteCallable(outputStream, typedInput);
		executor.submit(inputWriteThread);
	}
	
	public String OJResult(ProblemTest problemTest,ProblemTestResult testResult) {
		System.out.println("expected:"+problemTest.getProblemTestOutput());
		System.out.println("real:"+testResult.getResult());
		if(problemTest.getProblemTestOutput().equals(testResult.getResult())){
			return "AC";
		}else{
			return "WA";
		}
	}
}
