package com.jinhaoplus.oj.domain;

import java.util.Date;

public class ProblemSolution {
	private Integer solutionId;
	private Integer problemId;
	private String codeSubmit;
	private Integer solutionCoderId;
	private String solutionLanguage;
	private Date codeSubmitTime;
	private String finalOJResult;
	
	

	public Integer getSolutionId() {
		return solutionId;
	}


	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}


	public Integer getProblemId() {
		return problemId;
	}


	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}


	public void setSolutionCoderId(Integer solutionCoderId) {
		this.solutionCoderId = solutionCoderId;
	}


	public String getCodeSubmit() {
		return codeSubmit;
	}


	public void setCodeSubmit(String codeSubmit) {
		this.codeSubmit = codeSubmit;
	}

	


	public Integer getSolutionCoderId() {
		return solutionCoderId;
	}


	public Date getCodeSubmitTime() {
		return codeSubmitTime;
	}


	public void setCodeSubmitTime(Date codeSubmitTime) {
		this.codeSubmitTime = codeSubmitTime;
	}


	public String getSolutionLanguage() {
		return solutionLanguage;
	}


	public void setSolutionLanguage(String solutionLanguage) {
		this.solutionLanguage = solutionLanguage;
	}

	
	public String getFinalOJResult() {
		return finalOJResult;
	}


	public void setFinalOJResult(String finalOJResult) {
		this.finalOJResult = finalOJResult;
	}


	@Override
	public String toString() {
		return "ProblemSolution [problemId=" + problemId + ", codeSubmit=" + codeSubmit + "]";
	}
	
}
