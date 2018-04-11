package com.ymkj.bxld.dao.common.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.common.util.GenerateOrderNoUtil;
import com.ymkj.bxld.dao.common.ICommonDao;
import com.ymkj.bxld.domain.demo.BaseVO;

@Repository
public class CommonDaoImpl extends BaseDaoImpl<BaseVO> implements ICommonDao {

	@Override
	public String createOrderNo() {
		return GenerateOrderNoUtil.createAppNo(new Date());
	}
}
