package com.ymkj.bxld.service.order.impl;

import com.alibaba.fastjson.JSON;
import com.ymkj.bxld.dao.order.IInsurProOrderDao;
import com.ymkj.bxld.domain.order.InsurProOrderDO;
import com.ymkj.bxld.service.order.IInsurProOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lihhuimeng
 * @description: 订单产品关系信息serviceImpl
 * @date: 2017/12/17 15:07
 */

@Service
public class InsurProOrderServiceImpl implements IInsurProOrderService{
    private static final Logger LOGGER = LoggerFactory.getLogger(InsurProOrderServiceImpl.class);

    @Autowired
    private IInsurProOrderDao insurProOrderDao;

    @Override
    public long saveInsurProOrder(List<InsurProOrderDO> orderDOListParam) {
        LOGGER.info("******批量插入订单产品关系信息,param:<{}>", JSON.toJSONString(orderDOListParam));
        long insertCount = 0;
        try {
            insertCount = insurProOrderDao.insertInsurProOrder(orderDOListParam);
            LOGGER.info("******批量插入订单产品关系信息:resultCount：<{}>", insertCount);
        } catch (Exception e) {
            LOGGER.error("批量插入订单产品关系信息异常,exception:",e);
        }
        return insertCount;
    }
}
