package com.jinhaoplus.oj.util;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public class DisplayRunUtils {
	private DisplayRunUtils(){
		
	}
	
	public static ProblemSolution sourceForACE(ProblemSolution solution) {
		String source = solution.getCodeSubmit();
		String modifySource = source.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\"", "\\\\\"");
		solution.setCodeSubmit(modifySource);
		return solution;
	}
	
	public static ProblemTest displayTests(ProblemTest problemTest){
		problemTest.setProblemTestInput(problemTest.getProblemTestInput().replaceAll("\\n", "&crarr;<br>").replaceAll(" ", "&nbsp;"));
		problemTest.setProblemTestOutput(problemTest.getProblemTestOutput().replaceAll("\\n", "&crarr;<br>").replaceAll(" ", "&nbsp;"));
		return problemTest;
	}
	
	public static ProblemTest displayEditTests(ProblemTest problemTest){
		problemTest.setProblemTestInput(problemTest.getProblemTestInput().replaceAll(" ", "&nbsp;"));
		problemTest.setProblemTestOutput(problemTest.getProblemTestOutput().replaceAll(" ", "&nbsp;"));
		return problemTest;
	}
	
	public static ProblemTestResult displayResults(ProblemTestResult testResult){
		if(testResult.getTestInput()!=null)
			testResult.setTestInput(testResult.getTestInput().replaceAll("\\n", "&crarr;<br>").replaceAll(" ", "&nbsp;"));
		if(testResult.getTestOutput()!=null)
			testResult.setTestOutput(testResult.getTestOutput().replaceAll("\\n", "&crarr;<br>").replaceAll(" ", "&nbsp;"));
		testResult.setResult(testResult.getResult().replaceAll("\\n", "&crarr;<br>").replaceAll(" ", "&nbsp;"));
		return testResult;
	}
	
	public static Problem problemTinyMCE2DB(Problem problem){
		String problemContent = problem.getProblemContent();
		problemContent = problemContent.replaceAll("\'", "&apos;").replaceAll("\"", "&quot;").replaceAll("\\n", "");
		problem.setProblemContent(problemContent);
		return problem;
	}
	
	public static ProblemTest testTinyMCE2DB(ProblemTest problemTest){
		problemTest.setProblemTestInput(problemTest.getProblemTestInput().replaceAll("&nbsp;", " "));
		problemTest.setProblemTestOutput(problemTest.getProblemTestOutput().replaceAll("&nbsp;", " "));
		return problemTest;
	}
}
