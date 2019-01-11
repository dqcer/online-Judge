package com.jinhaoplus.oj.service;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.User;

public interface AccountsService {
	public CommonMessage signUp(User user);
	public CommonMessage login(User user);
	public User getUserByName(String name);
	public void updateUser(User user);
}
