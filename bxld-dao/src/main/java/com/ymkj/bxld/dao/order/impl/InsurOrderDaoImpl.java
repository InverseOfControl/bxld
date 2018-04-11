package com.ymkj.bxld.dao.order.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.order.IInsurOrderDao;
import com.ymkj.bxld.domain.order.InsurOrderDO;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import org.apache.commons.lang.ObjectUtils;
import org.apache.poi.ss.formula.functions.T;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lihhuimeng
 * @description: 订单dao实现
 * @date: 2017/12/9 15:19
 */

@Repository
public class InsurOrderDaoImpl extends BaseDaoImpl<InsurOrderVO> implements IInsurOrderDao {

    @Override
    public List<InsurOrderVO> findInsurOrderList(Map<String, Object> paramMap) {

        int pageNum = Integer.valueOf(ObjectUtils.toString(paramMap.get("pageNum")));
        int pageSize = Integer.valueOf(ObjectUtils.toString(paramMap.get("pageSize")));
        int limitStart = (pageNum - 1) * pageSize;
        paramMap.put("limitStart", limitStart);
        return this.getSessionTemplate().selectList("findInsurOrderList", paramMap);
    }

    @Override
    public InsurOrderVO findOne(InsurOrderVO orderVo) {
        return this.getSessionTemplate().selectOne("findOne", orderVo);
    }

    @Override
    public long updateOrder(InsurOrderVO orderVo) {
        return this.getSessionTemplate().update("updateOrder", orderVo);
    }

	@Override
	public List<InsurOrderVO> getOrderApplicationList(Map<String, Object> map) {
		return this.getSessionTemplate().selectList("getOrderApplicationList", map);
	}

    @Override
    public long insertOrder(InsurOrderDO insurOrderDO) {
        return this.getSessionTemplate().insert("insertOrder", insurOrderDO);
    }

}
