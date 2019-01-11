package com.jinhaoplus.oj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.DataAnalyseDao;
import com.jinhaoplus.oj.domain.DataAnalyseBean;
import com.jinhaoplus.oj.service.DataAnalyseService;


@Service
public class DataAnalyseServiceImpl implements DataAnalyseService{

	@Autowired
	DataAnalyseDao dataAnalyseDao;
	public void setDataAnalyseDao(DataAnalyseDao dataAnalyseDao) {
		this.dataAnalyseDao = dataAnalyseDao;
	}


	@Override
	public List<DataAnalyseBean> getTopCodersData() {
		return dataAnalyseDao.getTopCodersData();
	}

}
