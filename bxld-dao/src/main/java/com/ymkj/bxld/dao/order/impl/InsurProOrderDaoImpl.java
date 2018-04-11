package com.ymkj.bxld.dao.order.impl;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.order.IInsurProOrderDao;
import com.ymkj.bxld.domain.order.InsurProOrderDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lihhuimeng
 * @description: 订单提交
 * @date: 2017/12/17 15:12
 */

@Repository
public class InsurProOrderDaoImpl extends BaseDaoImpl<InsurProOrderDO> implements IInsurProOrderDao {
    @Override
    public long insertInsurProOrder(List<InsurProOrderDO> orderDOListParam) {
        return this.getSessionTemplate().insert("insertInsurProOrder", orderDOListParam);
    }
}
