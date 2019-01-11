package com.jinhaoplus.oj.service.langCore.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.common.LangCoreResolver;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.langCore.LangCoreService;


@Service
public class JavaCoreServiceImpl implements LangCoreService {

	@Autowired
	private LangCoreResolver langCoreResolver;
	
	
	public void setLangCoreResolver(LangCoreResolver langCoreResolver) {
		this.langCoreResolver = langCoreResolver;
	}

	@Override
	public CommonMessage compileCode(String path) {
		String[] commands = {"javac","Test.java"};
		String compileDir = path.substring(0, path.lastIndexOf("/"));
		CommonMessage message = langCoreResolver.compileCode(compileDir,commands);
		return message;
	}

	@Override
	public List<ProblemTestResult> ojRunCode(int problemId, int solutionId ,String path) {
		String[] commands = {"java","-cp",".","Test"};
		String runDir = path.substring(0, path.lastIndexOf("/"));
		List<ProblemTestResult> results = langCoreResolver.ojRunCode(problemId,solutionId,runDir,commands);
		return results;
	}

	@Override
	public ProblemTestResult cloudRunCode(String path,String cloudRunnerSyncCode) {
		String[] runCommands = {"java","-cp",".","Test"};
		String runDir = path.substring(0, path.lastIndexOf("/"));
		ProblemTestResult result = langCoreResolver.cloudRunCode(runDir, runCommands,cloudRunnerSyncCode);
		return result;
	}

	@Override
	public String createTempSourceFile(String fileOrDirName) {
		try {
			String path = fileOrDirName.substring(0,
					fileOrDirName.lastIndexOf("."))
					+ "/";
			File dir = new File(path);
			if(!dir.exists())
				dir.mkdir();
			return path + "Test.java";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void cloudRunInput(String typedInput, String cloudRunnerSyncCode) {
		langCoreResolver.cloudRunInput(typedInput, cloudRunnerSyncCode);
	}

	
}
