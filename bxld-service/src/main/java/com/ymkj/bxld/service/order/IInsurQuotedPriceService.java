package com.ymkj.bxld.service.order;

import com.ymkj.bxld.domain.order.InsurQuotedPriceDO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;

/**
 * @Author: lihhuimeng
 * @description: 报价信息
 * @date: 2017/12/12 16:42
 */
public interface IInsurQuotedPriceService {

    /**
     *
     * 插入订单报价
     * @param priceDO
     * @return
     * lihuimeng
     */
    long saveInsurQuotedPrice(InsurQuotedPriceDO priceDO);

    /**
     *
     * 更新订单报价
     * @param priceVO
     * @return
     * lihuimeng
     */
    long updateInsurQuotedPrice(InsurQuotedPriceVO priceVO);

    /**
     * @discripeion 根据订单编号查询报价信息
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     * @param insurQuotedPriceVO
     */
    InsurQuotedPriceVO getOrdeOfferInfo(InsurQuotedPriceVO insurQuotedPriceVO);

}
