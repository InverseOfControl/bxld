package com.ymkj.bxld.dao.order.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.order.InsurPaymentInfoDao;
import com.ymkj.bxld.domain.order.InsurPaymentInfoVO;

@Repository
public class InsurPaymentInfoDaoImpl extends BaseDaoImpl<InsurPaymentInfoVO> implements InsurPaymentInfoDao{

	@Override
	public List<InsurPaymentInfoVO> findPaymentInfoByOrderCode(String orderCode) {
		return this.getSessionTemplate().selectList("findPaymentInfo", orderCode);
	}

}
