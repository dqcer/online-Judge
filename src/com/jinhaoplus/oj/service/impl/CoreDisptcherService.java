package com.jinhaoplus.oj.service.impl;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.CoreDispatcherService;
import com.jinhaoplus.oj.service.ProblemsService;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
import com.jinhaoplus.oj.service.langCore.impl.CCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.CppCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.GoCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.HaskellCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.JavaCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.NodeCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.PhpCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.PyCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.RubyCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.SwiftCoreServiceImpl;
import com.jinhaoplus.oj.util.DisplayRunUtils;
import com.jinhaoplus.oj.util.PropertiesUtil;
import com.jinhaoplus.oj.util.Source2FileUtils;

@Service
public class CoreDisptcherService implements CoreDispatcherService{

	private final String COMPILE_SUCCESS_CODE = PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE");
	private final String COMPILE_SUCCESS = PropertiesUtil.getProperty("COMPILE_SUCCESS");
	private final String COMPILE_ERROR_CODE = PropertiesUtil.getProperty("COMPILE_ERROR_CODE");
	private final String COMPILE_ERROR = PropertiesUtil.getProperty("COMPILE_ERROR");

	private final String RUN_SUCCESS_CODE = PropertiesUtil.getProperty("RUN_SUCCESS_CODE");
	private final String RUN_ERROR_CODE = PropertiesUtil.getProperty("RUN_ERROR_CODE");
	
	
	private LangCoreService langCoreService = null;
	
	public void setLangCoreService(LangCoreService langCoreService) {
		this.langCoreService = langCoreService;
	}
	
	@Autowired
	private ProblemsService problemsService;
	
	public void setProblemsService(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}

	@Autowired
	private CCoreServiceImpl cCoreService;
	
	public void setcCoreService(CCoreServiceImpl cCoreService) {
		this.cCoreService = cCoreService;
	}
	
	@Autowired
	private PyCoreServiceImpl pyCoreService;
	
	public void setPyCoreService(PyCoreServiceImpl pyCoreService) {
		this.pyCoreService = pyCoreService;
	}
	
	@Autowired
	private JavaCoreServiceImpl javaCoreService;
	public void setJavaCoreService(JavaCoreServiceImpl javaCoreService) {
		this.javaCoreService = javaCoreService;
	}

	@Autowired
	private CppCoreServiceImpl cppCoreService;
	public void setCppCoreService(CppCoreServiceImpl cppCoreService) {
		this.cppCoreService = cppCoreService;
	}

	@Autowired
	private RubyCoreServiceImpl rubyCoreService;
	public void setRubyCoreService(RubyCoreServiceImpl rubyCoreService) {
		this.rubyCoreService = rubyCoreService;
	}

	@Autowired
	private HaskellCoreServiceImpl haskellCoreService;
	public void setHaskellCoreService(HaskellCoreServiceImpl haskellCoreService) {
		this.haskellCoreService = haskellCoreService;
	}
	
	@Autowired
	private GoCoreServiceImpl goCoreServiceImpl;
	public void setGoCoreServiceImpl(GoCoreServiceImpl goCoreServiceImpl) {
		this.goCoreServiceImpl = goCoreServiceImpl;
	}

	@Autowired
	private SwiftCoreServiceImpl swiftCoreServiceImpl;
	public void setSwiftCoreServiceImpl(SwiftCoreServiceImpl swiftCoreServiceImpl) {
		this.swiftCoreServiceImpl = swiftCoreServiceImpl;
	}

	@Autowired
	private NodeCoreServiceImpl nodeCoreServiceImpl;
	public void setNodeCoreServiceImpl(NodeCoreServiceImpl nodeCoreServiceImpl) {
		this.nodeCoreServiceImpl = nodeCoreServiceImpl;
	}
	
	@Autowired
	private PhpCoreServiceImpl phpCoreServiceImpl;
	public void setPhpCoreServiceImpl(PhpCoreServiceImpl phpCoreServiceImpl) {
		this.phpCoreServiceImpl = phpCoreServiceImpl;
	}


	@Override
	public void dispatchSolution(ProblemSolution solution) {
		String lang = solution.getSolutionLanguage();
		if("java".equals(lang)){
			langCoreService = javaCoreService;
		}
		else if ("c".equals(lang)) {
			langCoreService = cCoreService;
		}
		else if ("cpp".equals(lang)) {
			langCoreService = cppCoreService;
		}
		else if ("rb".equals(lang)) {
			langCoreService = rubyCoreService;
		}
		else if ("py".equals(lang)) {
			langCoreService = pyCoreService;
		}
		else if ("hs".equals(lang)) {
			langCoreService = haskellCoreService;
		}
		else if ("go".equals(lang)) {
			langCoreService = goCoreServiceImpl;
		}
		else if ("php".equals(lang)) {
			langCoreService = phpCoreServiceImpl;
		}
		else if ("swift".equals(lang)) {
			langCoreService = swiftCoreServiceImpl;
		}
		else if ("js".equals(lang)) {
			langCoreService = nodeCoreServiceImpl;
		}
	}


	@Override
	public List<ProblemTestResult> ojWorkFlow(ProblemSolution solution,String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		//write Code to temp file
		String fileOrDirName = path+Source2FileUtils.renameForTempSource(solution)+"."+solution.getSolutionLanguage();
		//workflow source file
		if(langCoreService!=null){
			String sourceFilePath = langCoreService.createTempSourceFile(fileOrDirName);
			
			Source2FileUtils.persistentFile(solution, sourceFilePath);
			CommonMessage message = langCoreService.compileCode(sourceFilePath);
			System.out.println("[+]compileinfo+"+message);
			if(COMPILE_SUCCESS_CODE.equals(message.getCode())){
				List<ProblemTestResult> results = langCoreService.ojRunCode(solution.getProblemId(),solution.getSolutionId() , sourceFilePath);
				String finalOJResult = "AC";
				for (ProblemTestResult problemTestResult : results) {
					DisplayRunUtils.displayResults(problemTestResult);
					if(!"AC".equals(problemTestResult.getOjResult())){
						finalOJResult = "WA";
					}
				}
				solution.setFinalOJResult(finalOJResult);
				problemsService.updateSolution(solution);
				return results;
			}else if(COMPILE_ERROR_CODE.equals(message.getCode())){
				solution.setFinalOJResult("CE");
				problemsService.updateSolution(solution);
				
				List<ProblemTestResult> results = new ArrayList<ProblemTestResult>();
				ProblemTestResult problemTestResult = new ProblemTestResult();
				problemTestResult.setMessage(message);
				results.add(problemTestResult);
				return results;
			}
		}
		return null;
	}


	@Override
	public ProblemTestResult cloudRunWorkFlow(ProblemSolution solution,String path,String cloudRunnerSyncCode) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileOrDirName = path+Source2FileUtils.renameForTempSource(solution)+"."+solution.getSolutionLanguage();
		if(langCoreService!=null){
			String sourceFilePath = langCoreService.createTempSourceFile(fileOrDirName);
			
			Source2FileUtils.persistentFile(solution, sourceFilePath);
			CommonMessage message = langCoreService.compileCode(sourceFilePath);
			System.out.println("[+]compileinfo+"+message);
			if(COMPILE_SUCCESS_CODE.equals(message.getCode())){
				ProblemTestResult result = langCoreService.cloudRunCode(sourceFilePath,cloudRunnerSyncCode);
				DisplayRunUtils.displayResults(result);
				return result;
			}else if(COMPILE_ERROR_CODE.equals(message.getCode())){
				ProblemTestResult result = new ProblemTestResult();
				result.setMessage(message);
				return result;
			}
		}
		return null;
	}


	@Override
	public void cloudRunInput(String typedInput, String cloudRunSyncCode) {
		langCoreService.cloudRunInput(typedInput, cloudRunSyncCode);
	}
}
