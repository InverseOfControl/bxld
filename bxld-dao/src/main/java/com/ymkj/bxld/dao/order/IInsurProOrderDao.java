package com.ymkj.bxld.dao.order;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.order.InsurOrderDO;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.order.InsurProOrderDO;

import java.util.List;
import java.util.Map;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:18
 */
public interface IInsurProOrderDao extends BaseDao<InsurProOrderDO> {

    /**
     * @param orderDOListParam
     * @discripeion 插入订单信息
     * @Author lihuimeng
     * @date 2017/12/17 14:26
     */
    long insertInsurProOrder(List<InsurProOrderDO> orderDOListParam);

}
