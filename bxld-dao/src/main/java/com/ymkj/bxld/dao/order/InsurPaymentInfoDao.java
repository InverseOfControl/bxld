package com.ymkj.bxld.dao.order;

import java.util.List;

import com.ymkj.bxld.domain.order.InsurPaymentInfoVO;

public interface InsurPaymentInfoDao {

	List<InsurPaymentInfoVO> findPaymentInfoByOrderCode(String string);

}
