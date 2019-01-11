package com.jinhaoplus.oj.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jinhaoplus.oj.dao.DataAnalyseDao;
import com.jinhaoplus.oj.domain.DataAnalyseBean;

@Repository
public class DataAnalyseDaoImpl extends SqlMapClientDaoSupport implements DataAnalyseDao{

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
	public List<DataAnalyseBean> getTopCodersData() {
		return getSqlMapClientTemplate().queryForList("getTopCodersData",null);
	}

}
