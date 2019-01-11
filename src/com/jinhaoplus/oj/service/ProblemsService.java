package com.jinhaoplus.oj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

@Service
public interface ProblemsService {
	public List<Problem> getAllProblems(int coderId);
	
	public int insertProblem(Problem problem);
	
	public Problem getProblemById(int id);
	
	public List<Problem> getProblemByPosterId(int posterId);
	
	public void updateProblem(Problem problem, ProblemSolution solution);
	
	public void deleteProblemById(int problemId);
	
	public List<ProblemTest> getTestsByProblemId(int problemId);
	
	public List<ProblemTest> getVisableTestsByProblemId(int problemId);
	
	public List<ProblemTest> getAllVisableTestsByProblemId(int problemId);
	
	public void updateProblemTest(ProblemTest problemTest);
	
	public void deleteTestsByProblemId(int problemId);
	
	public int insertProblemTest(ProblemTest problemTest);
	
	public int insertSolution(ProblemSolution problemSolution);
	
	public void updateSolution(ProblemSolution problemSolution);
	
	public List<ProblemSolution> getSolutions(ProblemSolution solution);
	
	public ProblemSolution getSpecSolution(int solutionId);
	
	public List<ProblemSolution> getMySolutions(int coderId);

	public void insertTestResult(ProblemTestResult testResult);
	
	public List<ProblemTestResult> getTestResultsBySolutionId(int solutionId);
	
	public void visableTestResults(List<ProblemTestResult> testResults);
	
}
