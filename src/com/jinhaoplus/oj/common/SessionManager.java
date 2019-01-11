package com.jinhaoplus.oj.common;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session" , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionManager{
	private HttpSession session;
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void syncedProcessToSession(String cloudRunnerSyncCode,Process cloudRunProcess){
		session.setAttribute(cloudRunnerSyncCode, cloudRunProcess);
	}
}
