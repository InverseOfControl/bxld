package com.ymkj.bxld.dao.order;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceDO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;

import java.util.List;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:18
 */
public interface IInsurQuotedPriceDao {

    /**
     *
     * 插入订单报价
     * @param priceDO
     * @return
     * lihuimeng
     */
    long insertInsurQuotedPrice(InsurQuotedPriceDO priceDO);

    /**
     * @discripeion 根据订单编号查询报价信息
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     * @param insurQuotedPriceVO
     */
    InsurQuotedPriceVO findOrdeOfferInfo(InsurQuotedPriceVO insurQuotedPriceVO);

    /**
     *
     * 更新订单报价
     * @param priceVO
     * @return
     * lihuimeng
     */
    long updateInsurQuotedPrice(InsurQuotedPriceVO priceVO);

}
