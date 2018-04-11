package com.ymkj.bxld.service.order;


import com.ymkj.bxld.domain.order.InsurProOrderDO;

import java.util.List;

/**
 * @Author: lihhuimeng
 * @description: 订单产品关系service
 * @date: 2017/12/9 15:16
 */
public interface IInsurProOrderService {

    /**
     * @discripeion 插入订单产品关系
     * @Author lihuimeng
     * @date 2017/12/17 15:09
     * @param orderDOListParam
     *
     */
    long saveInsurProOrder(List<InsurProOrderDO> orderDOListParam);

}
