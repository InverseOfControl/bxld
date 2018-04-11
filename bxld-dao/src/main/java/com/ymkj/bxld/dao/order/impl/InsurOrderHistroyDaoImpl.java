package com.ymkj.bxld.dao.order.impl;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.order.IInsurOrderDao;
import com.ymkj.bxld.dao.order.IInsurOrderHistoryDao;
import com.ymkj.bxld.domain.order.InsurOrderHistory;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lihhuimeng
 * @description: 订单dao实现
 * @date: 2017/12/9 15:19
 */

@Repository
public class InsurOrderHistroyDaoImpl extends BaseDaoImpl<InsurOrderHistory> implements IInsurOrderHistoryDao {

    @Override
    public long insertOrderHistory(InsurOrderHistory orderHistory) {
        return this.getSessionTemplate().insert("insertOrderHistory", orderHistory);
    }
}
