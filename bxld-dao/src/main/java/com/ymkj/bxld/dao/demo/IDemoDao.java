package com.ymkj.bxld.dao.demo;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.demo.DemoVO;

/**
 * 持久层
 * @author YM10159
 */
public interface IDemoDao extends BaseDao<DemoVO> {
	public String test(String id);
}
