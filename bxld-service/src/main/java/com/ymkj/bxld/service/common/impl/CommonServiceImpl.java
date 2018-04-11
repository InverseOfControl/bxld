package com.ymkj.bxld.service.common.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.bxld.dao.common.ICommonDao;
import com.ymkj.bxld.service.common.ICommonService;

@Service
public class CommonServiceImpl implements ICommonService {

	private static final Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private ICommonDao commonDao;
	
	@Override
	public String createOrderNo() {
		return commonDao.createOrderNo();
	}
	
}
