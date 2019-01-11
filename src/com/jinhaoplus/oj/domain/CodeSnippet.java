package com.jinhaoplus.oj.domain;

import java.util.Date;

public class CodeSnippet {
	private int codeSnippetsId;
	private String codeSnippit;
	private String snippetDescription;
	private Date snippetSavedDate;
	private int coderId;
	private String pubpri;
	public Date getSnippetSavedDate() {
		return snippetSavedDate;
	}
	public void setSnippetSavedDate(Date snippetSavedDate) {
		this.snippetSavedDate = snippetSavedDate;
	}
	public int getCodeSnippetsId() {
		return codeSnippetsId;
	}
	public void setCodeSnippetsId(int codeSnippetsId) {
		this.codeSnippetsId = codeSnippetsId;
	}
	public String getCodeSnippit() {
		return codeSnippit;
	}
	public void setCodeSnippit(String codeSnippit) {
		this.codeSnippit = codeSnippit;
	}
	public String getSnippetDescription() {
		return snippetDescription;
	}
	public void setSnippetDescription(String snippetDescription) {
		this.snippetDescription = snippetDescription;
	}
	
	public int getCoderId() {
		return coderId;
	}
	public void setCoderId(int coderId) {
		this.coderId = coderId;
	}
	public String getPubpri() {
		return pubpri;
	}
	public void setPubpri(String pubpri) {
		this.pubpri = pubpri;
	}
}
