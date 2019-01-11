package com.jinhaoplus.oj.service;

import java.util.List;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface CoreDispatcherService {
	public void dispatchSolution(ProblemSolution solution); 
	public List<ProblemTestResult> ojWorkFlow(ProblemSolution solution,String path);
	public ProblemTestResult cloudRunWorkFlow(ProblemSolution solution,String path,String cloudRunnerSyncCode);
	public void cloudRunInput(String typedInput,String cloudRunSyncCode);
}
