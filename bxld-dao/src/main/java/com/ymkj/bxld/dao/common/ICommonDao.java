package com.ymkj.bxld.dao.common;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.demo.BaseVO;

/**
 * 公共持久层
 * @author YM10159
 */
public interface ICommonDao extends BaseDao<BaseVO> {
	
	/**
	 * description:生成唯一的订单号
	 * autor:ym10159
	 * date:2017年12月9日 下午2:40:21
	 */
	public String createOrderNo();
}
