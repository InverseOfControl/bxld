package com.ymkj.bxld.dao.order;

import com.ymkj.bxld.domain.order.InsurOrderHistory;
import com.ymkj.bxld.domain.order.InsurOrderVO;

import java.util.List;

/**
 * @Author: lihhuimeng
 * @description: 订单操作历史信息dao
 * @date: 2017/12/9 15:18
 */
public interface IInsurOrderHistoryDao {

    /**
     * 插入
     * @param orderHistory
     * @return
     * lihuimeng
     */
    long insertOrderHistory(InsurOrderHistory orderHistory);

}
