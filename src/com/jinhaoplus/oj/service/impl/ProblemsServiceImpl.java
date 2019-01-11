package com.jinhaoplus.oj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.ProblemsService;
import com.jinhaoplus.oj.util.DisplayRunUtils;

@Service
public class ProblemsServiceImpl implements ProblemsService{

	@Autowired
	private ProblemsDao problemsDao;
	
	

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}



	@Override
	public List<Problem> getAllProblems(int coderId) {
		List<Problem> problems = problemsDao.getAllProblems();
		if(coderId != 0)
			problems = this.achieveSomePerProblemResult(problems,coderId);
		return problems;
	} 

	@Override
	public int insertProblem(Problem problem){
		return problemsDao.insertProblem(problem);
	}
	

	@Override
	public Problem getProblemById(int id) {
		return problemsDao.getProblemById(id);
	}
	
	@Override
	public List<Problem>  getProblemByPosterId(int posterId) {
		return problemsDao.getProblemByPosterId(posterId);
	}

	@Override
	public void updateProblem(Problem problem,ProblemSolution solution) {
		if(solution!=null){
			problem.setProblemSolveTimes(problem.getProblemSolveTimes()+1);
			if("AC".equals(solution.getFinalOJResult()))
				problem.setProblemAcTimes(problem.getProblemAcTimes()+1);
			else if("CE".equals(solution.getFinalOJResult()))
				problem.setProblemCeTimes(problem.getProblemCeTimes()+1);
			else if ("WA".equals(solution.getFinalOJResult())) {
				problem.setProblemWaTimes(problem.getProblemWaTimes()+1);
			}
		}
		problemsDao.updateProblem(problem);
	}
	
	@Override
	public void deleteProblemById(int problemId) {
		problemsDao.deleteProblemById(problemId);
		
	}


	@Override
	public List<ProblemTest> getTestsByProblemId(int problemId) {
		return problemsDao.getTestsByProblemId(problemId);
	}



	@Override
	public List<ProblemTest> getVisableTestsByProblemId(int problemId) {
		List<ProblemTest> visableProblemTests = problemsDao.getVisableTestsByProblemId(problemId);
		for (ProblemTest problemTest : visableProblemTests) {
			DisplayRunUtils.displayTests(problemTest);
		}
		return visableProblemTests;
	}
	
	@Override
	public List<ProblemTest> getAllVisableTestsByProblemId(int problemId) {
		List<ProblemTest> visableProblemTests = problemsDao.getAllVisableTestsByProblemId(problemId);
		for (ProblemTest problemTest : visableProblemTests) {
			DisplayRunUtils.displayEditTests(problemTest);
		}
		return visableProblemTests;
	}

	@Override
	public void updateProblemTest(ProblemTest problemTest){
		problemsDao.updateProblemTest(problemTest);
	}
	
	@Override
	public void deleteTestsByProblemId(int problemId){
		problemsDao.deleteTestsByProblemId(problemId);
	}
	
	@Override
	public int insertProblemTest(ProblemTest problemTest) {
		return problemsDao.insertProblemTest(problemTest);
	};

	@Override
	public void insertTestResult(ProblemTestResult testResult) {
		problemsDao.insertTestResult(testResult);
		
	}



	@Override
	public int insertSolution(ProblemSolution problemSolution) {
		return problemsDao.insertSolution(problemSolution);
		
	}
	
	@Override
	public void updateSolution(ProblemSolution problemSolution) {
		problemsDao.updateSolution(problemSolution);
		
	}

	@Override
	public List<ProblemSolution> getSolutions(ProblemSolution solution) {
		return problemsDao.getSolutions(solution);
	}
	
	@Override
	public ProblemSolution getSpecSolution(int solutionId){
		ProblemSolution paraSolution = new ProblemSolution();
		paraSolution.setSolutionId(solutionId);
		List<ProblemSolution> specSolutions = problemsDao.getSolutions(paraSolution);
		if(specSolutions != null)
			return specSolutions.get(0);
		else
			return null;
	}
	
	@Override
	public List<ProblemSolution> getMySolutions(int coderId){
		ProblemSolution paraSolution = new ProblemSolution();
		paraSolution.setSolutionCoderId(coderId);
		return problemsDao.getSolutionsNoCode(paraSolution);
	}

	@Override
	public List<ProblemTestResult> getTestResultsBySolutionId(int solutionId){
		List<ProblemTestResult> testResults = problemsDao.getTestResultsBySolutionId(solutionId);
		for (ProblemTestResult problemTestResult : testResults) {
			DisplayRunUtils.displayResults(problemTestResult);
		}
		return testResults;
	}
	
	@Override
	public void visableTestResults(List<ProblemTestResult> testResults){
		int problemId = testResults.get(0).getProblemId();
		List<ProblemTest> problemTests = problemsDao.getTestsByProblemId(problemId);
		List<Integer> hiddenTestsIds = new  ArrayList<Integer>();
		for (ProblemTest problemTest : problemTests) {
			if(problemTest.getProblemTestVisable()!=null && "0".equals(problemTest.getProblemTestVisable())){
				int aId = problemTest.getProblemTestId();
				hiddenTestsIds.add(aId);
			}
		}
		for (ProblemTestResult testResult : testResults) {
			if(hiddenTestsIds.contains(testResult.getTestId())){
				testResult.setTestInput("---HIDDEN INPUT---");
				testResult.setTestOutput("---HIDDEN EXPECTED OUTPUT---");
				testResult.setResult("---HIDDEN RESULT---");
			}
		}
	}
	
	public List<Problem> achieveSomePerProblemResult(List<Problem> problems,int coderId){
		for (Problem problem : problems) {
			ProblemSolution paraSolution = new ProblemSolution();
			paraSolution.setProblemId(problem.getProblemId());
			paraSolution.setSolutionCoderId(coderId);
			paraSolution.setFinalOJResult("AC");
			List<ProblemSolution> solutions = problemsDao.getSolutionsNoCode(paraSolution);
			if(solutions.size()>0){
				problem.setSomeUserResult("AC");
				continue;
			}
			paraSolution.setFinalOJResult("WA");
			solutions = problemsDao.getSolutionsNoCode(paraSolution);
			if(solutions.size()>0){
				problem.setSomeUserResult("WA");
				continue;
			}
			
			paraSolution.setFinalOJResult("CE");
			solutions = problemsDao.getSolutionsNoCode(paraSolution);
			if(solutions.size()>0){
				problem.setSomeUserResult("CE");
				continue;
			}
			problem.setSomeUserResult("NEW");
		}
		return problems;
	}
}
