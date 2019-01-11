package com.jinhaoplus.oj.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jinhaoplus.oj.dao.AccountsDao;
import com.jinhaoplus.oj.domain.User;


@Repository
public class AccountsDaoImpl extends SqlMapClientDaoSupport implements AccountsDao{

	@Resource(name = "dataSource")  
    private DataSource dataSource;  
    @PostConstruct  
        public void initDataSource(){  
         super.setDataSource(dataSource);
    }   

	@Resource(name = "sqlMapClient")  
    private SqlMapClient sqlMapClient;  
    @PostConstruct  
        public void initSqlMapClient(){  
         super.setSqlMapClient(sqlMapClient);  
    }   

	@Override
	public List<User> getList() {
		return getSqlMapClientTemplate().queryForList("getAllUsers",null);
	}

	@Override
	public User getByName(String name) {
		  return (User)getSqlMapClientTemplate().queryForObject("getUsersByName",name);
	}

	@Override
	public User getById(String id) {
		return (User)getSqlMapClientTemplate().queryForObject("getUsersById",id);
	}

	@Override
	public void insert(User user) {
		 getSqlMapClientTemplate().insert("insertUsers",user);
		
	}


	@Override
	public void updateUserByName(User user) {
		getSqlMapClientTemplate().update("updateUserByName", user);
	}

	@Override
	public String getBitCode(String bitCode) {
		return (String) getSqlMapClientTemplate().queryForObject("getCode",bitCode);
	}

}
