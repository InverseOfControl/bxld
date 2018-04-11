package com.ymkj.bxld.dao.weixin.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.weixin.IWXUserInfoDao;
import com.ymkj.bxld.domain.user.InsurUserInfo;

@Repository
public class WXUserInfoDaoImpl extends BaseDaoImpl<InsurUserInfo> implements IWXUserInfoDao {

	@Override
	public Map<String, Object> userIfAuth(InsurUserInfo userInfo) {
		return getSqlSession().selectOne("userIfAuth", userInfo);
	}
	
	@Override
	public int saveWXUserInfo(InsurUserInfo userInfo) {
		return getSqlSession().update("saveWXUserInfo", userInfo);
	}

	@Override
	public int userIfInCompany(InsurUserInfo userInfo) {
		return getSqlSession().selectOne("userIfInCompany", userInfo);
	}
	
}
