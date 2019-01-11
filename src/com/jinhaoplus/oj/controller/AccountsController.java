package com.jinhaoplus.oj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.common.SessionManager;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.AccountsService;
import com.jinhaoplus.oj.util.SecurityUtils;

@Controller
@RequestMapping(value="/accounts")
public class AccountsController {
	
	@Autowired
	private AccountsService accountsService;
	public void setAccountsService(AccountsService accountsService) {
		this.accountsService = accountsService;
	}
	
	@Autowired
	private SessionManager sessionManager;
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@RequestMapping(value="/tosignup")
	public ModelAndView toSignUp(HttpServletRequest request,HttpServletResponse response,User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signup");
		modelAndView.addObject("some", "jinhaoluo");
		return modelAndView;
	}
	
	@RequestMapping(value="/signup")
	public ModelAndView signUp(HttpServletRequest request,HttpServletResponse response,User user) {
		ModelAndView modelAndView = new ModelAndView();
		CommonMessage message = accountsService.signUp(user);
		if(!"203".equals(message.getCode())){
			modelAndView.setViewName("signup");
			modelAndView.addObject("signupInfo", message.getMessage());
		}else{
			modelAndView.setViewName("signup-success");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/changePassword")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response,User user) {
		ModelAndView modelAndView = new ModelAndView();
		String oldPassword = request.getParameter("old_password");
		String newPassword = request.getParameter("new_password");
		user.setPassword(oldPassword);
		CommonMessage message = accountsService.login(user);
		if(!"202".equals(message.getCode())){
			modelAndView.setViewName("profile");
			modelAndView.addObject("show_toggle", "yes");
			modelAndView.addObject("new_password", newPassword);
			modelAndView.addObject("chgpsdInfo", "Old Password Invalid");
		}else{
			user.setPassword(SecurityUtils.AESEncrypt(newPassword));
			accountsService.updateUser(user);
			modelAndView.setViewName("signup-success");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/profile")
	public ModelAndView profile(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile");
		User user = (User) request.getSession().getAttribute("loginuser");
		modelAndView.addObject("loginuser", user);
		return modelAndView;
	}
	
	@RequestMapping(value="/login")
	public void login(HttpServletRequest request,HttpServletResponse response,User user) throws IOException, ServletException {
		CommonMessage message = accountsService.login(user);
		if(!"202".equals(message.getCode())){
			request.setAttribute("loginInfo", message.getMessage());
			request.getRequestDispatcher("/accounts/tologin").forward(request, response);
			return;
		}
		user = accountsService.getUserByName(user.getUsername());
		request.getSession().setAttribute("loginuser", user);
		sessionManager.setSession(request.getSession());
		response.sendRedirect(request.getContextPath()+"/index");
		
	}
	
	@RequestMapping(value="/logout")
	public void loginout(HttpServletRequest request,HttpServletResponse response,User user) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/index");
	}
	
	@RequestMapping(value="/tologin")
	public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response,User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		request.getSession().setAttribute("user", user);
		modelAndView.addObject("loginInfo", request.getAttribute("loginInfo"));
		return modelAndView;
	}
	
	
}
