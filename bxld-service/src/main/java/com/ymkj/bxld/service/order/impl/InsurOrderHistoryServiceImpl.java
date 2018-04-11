package com.ymkj.bxld.service.order.impl;

import com.alibaba.fastjson.JSON;

import com.ymkj.bxld.dao.order.IInsurOrderHistoryDao;
import com.ymkj.bxld.domain.order.InsurOrderHistory;
import com.ymkj.bxld.service.order.IInsurOrderHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:17
 */

@Service
public class InsurOrderHistoryServiceImpl implements IInsurOrderHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsurOrderHistoryServiceImpl.class);

    @Autowired
    private IInsurOrderHistoryDao orderHistoryDao;

    @Override
    public long insertOrderHistory(InsurOrderHistory orderHistory) {

        LOGGER.info("******插入订单历史信息,param:<{}>", JSON.toJSONString(orderHistory));
        long insertCount = 0;
        try {
            insertCount = orderHistoryDao.insertOrderHistory(orderHistory);
            LOGGER.info("******插入订单历史信息:resultCount：<{}>", insertCount);
        } catch (Exception e) {
            LOGGER.error("插入订单历史信息失败,订单编号：<{}>,exception:", orderHistory.getOrderCode(),e);
        }
        return insertCount;
    }
}

