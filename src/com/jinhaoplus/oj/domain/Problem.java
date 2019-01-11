package com.jinhaoplus.oj.domain;

public class Problem{
	private Integer problemId;
	private String problemDigest;
	private String problemCategory;
	private String problemContent;
	private String problemLanguage;
	private Integer problemAcTimes;
	private Integer problemWaTimes;
	private Integer problemCeTimes;
	private Integer problemSolveTimes;
	private String someUserResult;
	private String problemVisable;
	private String problemPoster;
	
	public Integer getProblemId() {
		return problemId;
	}
	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	public String getProblemDigest() {
		return problemDigest;
	}
	public void setProblemDigest(String problemDigest) {
		this.problemDigest = problemDigest;
	}
	public String getProblemCategory() {
		return problemCategory;
	}
	public void setProblemCategory(String problemCategory) {
		this.problemCategory = problemCategory;
	}
	public String getProblemContent() {
		return problemContent;
	}
	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}
	public String getProblemLanguage() {
		return problemLanguage;
	}
	public void setProblemLanguage(String problemLanguage) {
		this.problemLanguage = problemLanguage;
	}
	
	public Integer getProblemAcTimes() {
		return problemAcTimes;
	}
	public void setProblemAcTimes(Integer problemAcTimes) {
		this.problemAcTimes = problemAcTimes;
	}
	
	public Integer getProblemWaTimes() {
		return problemWaTimes;
	}
	public void setProblemWaTimes(Integer problemWaTimes) {
		this.problemWaTimes = problemWaTimes;
	}
	public Integer getProblemCeTimes() {
		return problemCeTimes;
	}
	public void setProblemCeTimes(Integer problemCeTimes) {
		this.problemCeTimes = problemCeTimes;
	}
	public Integer getProblemSolveTimes() {
		return problemSolveTimes;
	}
	public void setProblemSolveTimes(Integer problemSolveTimes) {
		this.problemSolveTimes = problemSolveTimes;
	}
	public String getSomeUserResult() {
		return someUserResult;
	}
	public void setSomeUserResult(String someUserResult) {
		this.someUserResult = someUserResult;
	}
	public String getProblemVisable() {
		return problemVisable;
	}
	public void setProblemVisable(String problemVisable) {
		this.problemVisable = problemVisable;
	}
	public String getProblemPoster() {
		return problemPoster;
	}
	public void setProblemPoster(String problemPoster) {
		this.problemPoster = problemPoster;
	}
	
}
