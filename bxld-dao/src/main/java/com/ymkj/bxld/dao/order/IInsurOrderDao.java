package com.ymkj.bxld.dao.order;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.demo.DemoVO;
import com.ymkj.bxld.domain.order.InsurOrderDO;
import com.ymkj.bxld.domain.order.InsurOrderVO;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import java.util.Map;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:18
 */
public interface IInsurOrderDao extends BaseDao<InsurOrderVO> {

    /**
     * 订单列表查询
     *
     * @param paramMap
     * @author lihuimeng
     * lihuimeng
     */
    List<InsurOrderVO> findInsurOrderList(Map<String, Object> paramMap);

    /**
     * 单个查找
     *
     * @param order
     * @return
     */
    InsurOrderVO findOne(InsurOrderVO order);

    /**
     * 更新订单信息
     *
     * @param orderVo
     * @return
     */
    long updateOrder(InsurOrderVO orderVo);

    /**
     * 微信端查询订单列表
     * @param map
     * @return
     */
	List<InsurOrderVO> getOrderApplicationList(Map<String, Object> map);

    /**
     * @param insurOrderDO
     * @discripeion 插入订单信息
     * @Author lihuimeng
     * @date 2017/12/17 14:26
     */
    long insertOrder(InsurOrderDO insurOrderDO);


}
