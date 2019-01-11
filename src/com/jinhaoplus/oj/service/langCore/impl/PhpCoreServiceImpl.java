package com.jinhaoplus.oj.service.langCore.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.common.LangCoreResolver;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
import com.jinhaoplus.oj.util.PropertiesUtil;

@Service
public class PhpCoreServiceImpl implements LangCoreService {

	@Autowired
	private LangCoreResolver langCoreResolver;
	
	
	public void setLangCoreResolver(LangCoreResolver langCoreResolver) {
		this.langCoreResolver = langCoreResolver;
	}
	
	@Override
	//Interpreted Language doesn't need to be compiled : directly from source to be executable
	public CommonMessage compileCode(String path) {
		CommonMessage message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE"), 
				PropertiesUtil.getProperty("COMPILE_SUCCESS"), 
				"");
		return message;
	}

	@Override
	public List<ProblemTestResult> ojRunCode(int problemId, int solutionId ,  String path) {
		return null;
	}

	@Override
	public ProblemTestResult cloudRunCode(String path,String cloudRunnerSyncCode) {
		String destFileName = path.substring(path.lastIndexOf("/")+1, path.length());
		String[] runCommands = {"php",destFileName};
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
