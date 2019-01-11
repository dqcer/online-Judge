package com.jinhaoplus.oj.service.langCore.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.common.LangCoreResolver;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.langCore.LangCoreService;


@Service
public class CppCoreServiceImpl implements LangCoreService {

	
	@Autowired
	private LangCoreResolver langCoreResolver;
	
	
	public void setLangCoreResolver(LangCoreResolver langCoreResolver) {
		this.langCoreResolver = langCoreResolver;
	}
	
	@Override
	public CommonMessage compileCode(String path) {
		String destFileName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
		String sourceFileName = path.substring(path.lastIndexOf("/")+1,path.length());
		String[] commands = {"g++","-o",destFileName,sourceFileName};
		String compileDir = path.substring(0, path.lastIndexOf("/"));
		CommonMessage message = langCoreResolver.compileCode(compileDir,commands);
		return message;
	}

	@Override
	public List<ProblemTestResult> ojRunCode(int problemId, int solutionId ,  String path) {
		String destFileName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
		String[] commands = {"./"+destFileName};
		String runDir = path.substring(0, path.lastIndexOf("/"));
		List<ProblemTestResult> results = langCoreResolver.ojRunCode(problemId,solutionId,runDir,commands);
		return results;
	}

	@Override
	public ProblemTestResult cloudRunCode(String path,String cloudRunnerSyncCode) {
		String destFileName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
		String[] runCommands = {"./"+destFileName};
		String runDir = path.substring(0, path.lastIndexOf("/"));
		ProblemTestResult result = langCoreResolver.cloudRunCode(runDir, runCommands,cloudRunnerSyncCode);
		return result;
	}

	@Override
	public String createTempSourceFile(String fileOrDirName) {
		return fileOrDirName;
	}
	
	@Override
	public void cloudRunInput(String typedInput, String cloudRunnerSyncCode) {
		langCoreResolver.cloudRunInput(typedInput, cloudRunnerSyncCode);
	}

	
}
