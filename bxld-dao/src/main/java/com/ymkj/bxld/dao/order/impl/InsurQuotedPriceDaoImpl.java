package com.ymkj.bxld.dao.order.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.order.IInsurQuotedPriceDao;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceDO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;
import org.springframework.stereotype.Repository;

/**
 * @Author: lihhuimeng
 * @description: 订单dao实现
 * @date: 2017/12/9 15:19
 */

@Repository
public class InsurQuotedPriceDaoImpl extends BaseDaoImpl<InsurQuotedPriceDO> implements IInsurQuotedPriceDao {

    @Override
    public long insertInsurQuotedPrice(InsurQuotedPriceDO priceDO) {
        return this.getSessionTemplate().insert("insertInsurQuotedPrice", priceDO);
    }


    @Override
    public InsurQuotedPriceVO findOrdeOfferInfo(InsurQuotedPriceVO insurQuotedPriceVO) {
        return this.getSessionTemplate().selectOne("findOrdeOfferInfo", insurQuotedPriceVO);
    }

    @Override
    public long updateInsurQuotedPrice(InsurQuotedPriceVO priceVO) {
        return this.getSessionTemplate().update("updateInsurQuotedPrice", priceVO);
    }

}
