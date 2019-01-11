package com.jinhaoplus.oj.domain;

public class ProblemTestResult {
	private int resultId;
	private int problemId;
	private int solutionId;
	private int testId;
	private String testInput;
	private String testOutput;
	private String result;
	private String ojResult;
	private CommonMessage message;
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getProblemId() {
		return problemId;
	}
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	
	public int getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}
	public int getTestId() {
		return testId;
	}
	
	public String getTestInput() {
		return testInput;
	}
	public void setTestInput(String testInput) {
		this.testInput = testInput;
	}
	
	public String getTestOutput() {
		return testOutput;
	}
	public void setTestOutput(String testOutput) {
		this.testOutput = testOutput;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOjResult() {
		return ojResult;
	}
	public void setOjResult(String ojResult) {
		this.ojResult = ojResult;
	}
	public CommonMessage getMessage() {
		return message;
	}
	public void setMessage(CommonMessage message) {
		this.message = message;
	}
	
	public ProblemTestResult(){
		super();
	}
	
	public ProblemTestResult(int problemId, int testId, String result, String ojResult,
			CommonMessage message) {
		super();
		this.problemId = problemId;
		this.testId = testId;
		this.result = result;
		this.ojResult = ojResult;
		this.message = message;
	}
	@Override
	public String toString() {
		return "ProblemTestResult [resultId=" + resultId + ", problemId=" + problemId + ", testId=" + testId
				+ ", result=" + result + ", ojResult=" + ojResult + "]";
	}
	
}
