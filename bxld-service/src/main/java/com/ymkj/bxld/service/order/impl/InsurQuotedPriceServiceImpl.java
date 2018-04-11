package com.ymkj.bxld.service.order.impl;

import com.alibaba.fastjson.JSON;
import com.ymkj.bxld.dao.order.IInsurQuotedPriceDao;
import com.ymkj.bxld.domain.order.InsurQuotedPriceDO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;
import com.ymkj.bxld.service.order.IInsurQuotedPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lihhuimeng
 * @description: 订单报价serviceImpl
 * @date: 2017/12/9 15:17
 */

@Service
public class InsurQuotedPriceServiceImpl implements IInsurQuotedPriceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsurQuotedPriceServiceImpl.class);

    @Autowired
    private IInsurQuotedPriceDao priceDao;

    @Override
    public long saveInsurQuotedPrice(InsurQuotedPriceDO priceDO) {
        LOGGER.info("******插入订单报价信息,param:<{}>", JSON.toJSONString(priceDO));
        long insertCount = 0;
        try {
            insertCount = priceDao.insertInsurQuotedPrice(priceDO);
            LOGGER.info("******插入订单报价信息:resultCount：<>", insertCount);
        } catch (Exception e) {
            LOGGER.error("插入订单报价信息失败,订单编号：<{}>,exception:", priceDO.getOrderCode(),e);
        }
        return insertCount;
    }

    @Override
    public long updateInsurQuotedPrice(InsurQuotedPriceVO priceVO) {
        LOGGER.info("******更新订单报价信息,param:<{}>", JSON.toJSONString(priceVO));
        long insertCount = 0;
        try {
            insertCount = priceDao.updateInsurQuotedPrice(priceVO);
            LOGGER.info("******更新订单报价信息:resultCount：<>", insertCount);
        } catch (Exception e) {
            insertCount = -1;
            LOGGER.error("更新订单报价信息失败,订单编号：<{}>,exception:", priceVO.getOrderCode(),e);
        }
        return insertCount;
    }

    @Override
    public InsurQuotedPriceVO getOrdeOfferInfo(InsurQuotedPriceVO insurQuotedPriceVO) {
        LOGGER.info("根据单号报价信息, param：<{}>",JSON.toJSONString(insurQuotedPriceVO));
        return priceDao.findOrdeOfferInfo(insurQuotedPriceVO);
    }
}
