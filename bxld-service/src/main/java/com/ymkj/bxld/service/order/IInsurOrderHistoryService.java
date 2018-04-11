package com.ymkj.bxld.service.order;

import com.ymkj.bxld.domain.order.InsurOrderHistory;


/**
 * @Author: lihuimeng
 * @description: 订单操作历史service
 * @date: 2017/12/9 15:16
 */
public interface IInsurOrderHistoryService {

    /**
     * 插入
     * @param orderHistory
     * @return
     * lihuimeng
     */
    long insertOrderHistory(InsurOrderHistory orderHistory);
}