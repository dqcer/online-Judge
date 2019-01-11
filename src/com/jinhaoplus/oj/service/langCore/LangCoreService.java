package com.jinhaoplus.oj.service.langCore;

import java.util.List;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface LangCoreService {
	//persist source to file to store
	public String createTempSourceFile(String fileOrDirName);
	//Compile source Code
	public CommonMessage compileCode(String path);
	//Run compiled Code for oj
	public List<ProblemTestResult> ojRunCode(int problemId,int solutionId,String path);
	//Run compiled Code for cloudRunner
	public ProblemTestResult cloudRunCode(String path,String cloudRunnerSyncCode);
	//Receive the input from the webpage
	public void cloudRunInput(String typedInput,String cloudRunnerSyncCode);
}
