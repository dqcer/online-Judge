package com.jinhaoplus.oj.dao;

import java.util.List;

import com.jinhaoplus.oj.domain.User;

public interface AccountsDao {

	public List<User> getList();
	
	public String getBitCode(String bitCode);

	public User getByName(String name);

	public User getById(String id);

	public void insert(User user);

	public void updateUserByName(User user);

}
