package com.jinhaoplus.oj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.AccountsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.AccountsService;
import com.jinhaoplus.oj.util.PropertiesUtil;
import com.jinhaoplus.oj.util.SecurityUtils;


@Service
public class AccountsServiceImpl implements AccountsService{

	private final String LOGIN_SUCCESS_CODE = PropertiesUtil.getProperty("LOGIN_SUCCESS_CODE");
	private final String LOGIN_SUCCESS = PropertiesUtil.getProperty("LOGIN_SUCCESS");
	private final String LOGIN_ERROR_CODE = PropertiesUtil.getProperty("LOGIN_ERROR_CODE");
	private final String LOGIN_ERROR = PropertiesUtil.getProperty("LOGIN_ERROR");
	
	private final String SIGNUP_SUCCESS_CODE = PropertiesUtil.getProperty("SIGNUP_SUCCESS_CODE");
	private final String SIGNUP_SUCCESS = PropertiesUtil.getProperty("SIGNUP_SUCCESS");
	private final String SIGNUP_ERROR_CODE = PropertiesUtil.getProperty("SIGNUP_ERROR_CODE");
	private final String SIGNUP_ERROR = PropertiesUtil.getProperty("SIGNUP_ERROR");
	
	
	@Autowired
	private AccountsDao accountsDao;
	
	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	@Override
	public CommonMessage signUp(User user) {
		user.setPassword(SecurityUtils.AESEncrypt(user.getPassword()));
		User probUser = accountsDao.getByName(user.getUsername());
		String code = accountsDao.getBitCode(user.getBitcode());
		if(code!=null&&!"".equals(code)&&probUser == null){
			accountsDao.insert(user);
			return new CommonMessage(SIGNUP_SUCCESS_CODE, SIGNUP_SUCCESS,"");
		}
		else
			return new CommonMessage(SIGNUP_ERROR_CODE, SIGNUP_ERROR,"");
	}

	@Override
	public CommonMessage login(User user) {
		user.setPassword(SecurityUtils.AESEncrypt(user.getPassword()));
		User probUser = accountsDao.getByName(user.getUsername());
		if(probUser != null && probUser.getPassword().equals(user.getPassword())){
			return new CommonMessage(LOGIN_SUCCESS_CODE, LOGIN_SUCCESS,"");
		}
		else {
			return new CommonMessage(LOGIN_ERROR_CODE, LOGIN_ERROR,"");
		}
	}

	@Override
	public User getUserByName(String name) {
		return accountsDao.getByName(name);
	}

	@Override
	public void updateUser(User user) {
		accountsDao.updateUserByName(user);
	}

}
