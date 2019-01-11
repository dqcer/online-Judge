package com.jinhaoplus.oj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.CoreDispatcherService;
import com.jinhaoplus.oj.service.ProblemsService;
import com.jinhaoplus.oj.util.DisplayRunUtils;
import com.jinhaoplus.oj.util.ProblemUtils;

@Controller
@RequestMapping(value="/problems")
public class ProblemsController {
	@Autowired
	private ProblemsService problemsService;
	
	public void setProblemsService(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}
	
	@Autowired
	private CoreDispatcherService coreDispatcherService;

	public void setCoreDispatcherService(CoreDispatcherService coreDispatcherService) {
		this.coreDispatcherService = coreDispatcherService;
	}

	@RequestMapping(value="/{problemid}")
	public ModelAndView chooseProblem(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemid") String problemId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail");
		Problem problem = problemsService.getProblemById(Integer.parseInt(problemId));
		modelAndView.addObject("chosenProblem", problem);
		modelAndView.addObject("problemLanguages", Arrays.asList(problem.getProblemLanguage().split("&")));
		List<ProblemTest> displayTests = problemsService.getVisableTestsByProblemId(Integer.parseInt(problemId));
		modelAndView.addObject("displayTests",displayTests);
		return modelAndView;
	}
	
	@RequestMapping(value="/submitCode")
	@ResponseBody
	public ModelAndView submitCode(HttpServletRequest request,HttpServletResponse response,ProblemSolution solution){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("solutionDetail");
		
		Problem problem = problemsService.getProblemById(solution.getProblemId());
		
		coreDispatcherService.dispatchSolution(solution);
		int userId = ((User)request.getSession().getAttribute("loginuser")).getUserid();
		//for Linux server and OS X Server
		String sourceWaitPath = request.getRealPath("")+"sourceWait/";
		//for Windows Server
//		String sourceWaitPath = request.getRealPath("")+"/sourceWait/";
		solution.setSolutionCoderId(userId);
		solution.setCodeSubmitTime(new Date());
		int solutionId = problemsService.insertSolution(solution);
		solution.setSolutionId(solutionId);
		
		
		List<ProblemTestResult> testResults = coreDispatcherService.ojWorkFlow(solution,sourceWaitPath);
		problemsService.visableTestResults(testResults);
		modelAndView.addObject("testResults",testResults);
		
		CommonMessage compileMessage = testResults.get(0).getMessage();
		String compileResultCode = compileMessage.getCode();
		modelAndView.addObject("compileMessage", compileMessage);
		if("500".equals(compileResultCode))
			solution.setFinalOJResult("CE");
		modelAndView.addObject("compileResult", compileResultCode);
		
		solution = DisplayRunUtils.sourceForACE(solution);
		modelAndView.addObject("solution", solution);
		problemsService.updateProblem(problem, solution);
		
		problem = problemsService.getProblemById(solution.getProblemId());
		modelAndView.addObject("chosenProblem", problem);
		return modelAndView;
	}
	
	@RequestMapping(value="/cloudRunSync")
	@ResponseBody
	public String cloudRunSyncCode(HttpServletRequest request,HttpServletResponse response){
		StringBuffer buffer = new StringBuffer();
		buffer.append("cloudRunSync");
		buffer.append(((User)request.getSession().getAttribute("loginuser")).getUserid());
		buffer.append(System.currentTimeMillis());
		String cloudRunnerSyncCode = buffer.toString();
		return cloudRunnerSyncCode;
	}
	
	
	@RequestMapping(value="/cloudRun")
	@ResponseBody
	public ProblemTestResult cloudRun(HttpServletRequest request,HttpServletResponse response,ProblemSolution solution){
		coreDispatcherService.dispatchSolution(solution);
		
		int userId = ((User)request.getSession().getAttribute("loginuser")).getUserid();
		String cloudRunSyncCode = request.getParameter("cloudRunnerSyncCode");
		//for Linux server and OS X Server
		String sourceWaitPath = request.getRealPath("")+"sourceWait/";
		//for Windows Server
//		String sourceWaitPath = request.getRealPath("")+"/sourceWait/";
		solution.setSolutionCoderId(userId);
		solution.setCodeSubmitTime(new Date());
		ProblemTestResult testResult = coreDispatcherService.cloudRunWorkFlow(solution, sourceWaitPath,cloudRunSyncCode);
		return testResult;
	}
	
	@RequestMapping(value="/cloudRunEnterInput")
	@ResponseBody
	public void cloudRunEnterInput(HttpServletRequest request,HttpServletResponse response,ProblemSolution solution){
		String typedInput = request.getParameter("typedInput");
		String cloudRunnerSyncCode = request.getParameter("cloudRunnerSyncCode");
		coreDispatcherService.cloudRunInput(typedInput, cloudRunnerSyncCode);
	}
	
	
	@RequestMapping(value="/getSolutionDetail/{problemId}/{solutionId}")
	public ModelAndView getSolutionDetail(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemId") String problemId,@PathVariable("solutionId") String solutionId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("solutionDetail");
		
		Problem problem = problemsService.getProblemById(Integer.parseInt(problemId));
		modelAndView.addObject("chosenProblem", problem);
		
		ProblemSolution solution = problemsService.getSpecSolution(Integer.parseInt(solutionId));
		solution = DisplayRunUtils.sourceForACE(solution);
		modelAndView.addObject("solution", solution);
		
		List<ProblemTestResult> testResults = problemsService.getTestResultsBySolutionId(Integer.parseInt(solutionId));
		problemsService.visableTestResults(testResults);
		modelAndView.addObject("testResults",testResults);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/editProblem/{problemId}")
	@ResponseBody
	public ModelAndView editProblem(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemId") String problemId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("postOne");
		Problem problem = problemsService.getProblemById(Integer.parseInt(problemId));
		modelAndView.addObject("problem", problem);
		List<ProblemTest> problemTests = problemsService.getAllVisableTestsByProblemId(Integer.parseInt(problemId));
		modelAndView.addObject("problemTests", problemTests);
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteProblem/{problemId}")
	@ResponseBody
	public int deleteProblem(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemId") String problemId){
		int id = Integer.parseInt(problemId);
		problemsService.deleteProblemById(id);
		problemsService.deleteTestsByProblemId(id);
		return id;
	}
	
	@RequestMapping(value="/tempSaveProblemContent",method=RequestMethod.POST)
	@ResponseBody
	public int tempSaveProblemContent(HttpServletRequest request,HttpServletResponse response,Problem problem){
		int userId = ((User)request.getSession().getAttribute("loginuser")).getUserid();
		int yetProblemId = -1;
		DisplayRunUtils.problemTinyMCE2DB(problem);
		//No such problem yet
		if(problem.getProblemId()==null){
			ProblemUtils.initProblemInfo(problem, userId);
			yetProblemId = problemsService.insertProblem(problem);
		}
		//Have this problem already
		else {
			problemsService.updateProblem(problem, null);
			yetProblemId = problem.getProblemId();
		}
		return yetProblemId;
	}
	
	@RequestMapping(value="/saveProblemContent",method=RequestMethod.POST)
	@ResponseBody
	public int saveProblemContent(HttpServletRequest request,HttpServletResponse response,Problem problem){
		int userId = ((User)request.getSession().getAttribute("loginuser")).getUserid();
		int yetProblemId = -1;
		DisplayRunUtils.problemTinyMCE2DB(problem);
		//No such problem yet
		if(problem.getProblemId()==null){
			ProblemUtils.postProblemInfo(problem, userId);
			yetProblemId = problemsService.insertProblem(problem);
		}
		//Have this problem already
		else {
			ProblemUtils.postProblemInfo(problem, userId);
			problemsService.updateProblem(problem, null);
			yetProblemId = problem.getProblemId();
		}
		return yetProblemId;
	}
	
	@RequestMapping(value="/tempSaveProblemTest",method=RequestMethod.POST)
	@ResponseBody
	public Integer[] tempSaveProblemTest(HttpServletRequest request,HttpServletResponse response,@RequestBody ProblemTest[] problemTests){
		List<Integer> retTestIds = new ArrayList<Integer>();
		String newProblemId = request.getParameter("newProblem");
		for (ProblemTest problemTest : problemTests) {
			if(problemTest.getProblemTestId()!=null&&problemTest.getProblemId()!=null){
				DisplayRunUtils.testTinyMCE2DB(problemTest);
				problemsService.updateProblemTest(problemTest);
				retTestIds.add(problemTest.getProblemTestId());
			}else {
				DisplayRunUtils.testTinyMCE2DB(problemTest);
				problemTest.setProblemId(Integer.parseInt(newProblemId));
				int newTestId = problemsService.insertProblemTest(problemTest);
				retTestIds.add(newTestId);
			}
		}
		return retTestIds.toArray(new Integer[0]);
	}
}
