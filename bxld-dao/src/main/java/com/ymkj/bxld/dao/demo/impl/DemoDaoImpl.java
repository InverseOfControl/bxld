package com.ymkj.bxld.dao.demo.impl;

import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.demo.IDemoDao;
import com.ymkj.bxld.domain.demo.DemoVO;

@Repository
public class DemoDaoImpl extends BaseDaoImpl<DemoVO> implements IDemoDao {

	@Override
	public String test(String id) {
		Map<String,Object> map = this.getSessionTemplate().selectOne("test", id);
		return "姓名："+ObjectUtils.toString(map.get("name"))+" 身份证："+ObjectUtils.toString(map.get("id_no"));
	}
}
