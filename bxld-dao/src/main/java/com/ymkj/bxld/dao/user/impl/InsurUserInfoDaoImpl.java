package com.ymkj.bxld.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.user.InsurUserInfoDao;
import com.ymkj.bxld.domain.user.InsurUserInfo;

@Repository
public class InsurUserInfoDaoImpl extends BaseDaoImpl<InsurUserInfo> implements InsurUserInfoDao {

	

	@Override
	public List<InsurUserInfo> getUserListByLoginAccount(String loginAccount) {
		List<InsurUserInfo> userList = this.getSessionTemplate().selectList("getUserListByLoginAccount", loginAccount);
		return userList;
	}


	@Override
	public int updateAuthenticationBatchList(String idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		map.put("colum", "authentication_state");
		map.put("destinData", "1");
		int i = this.getSessionTemplate().update("authenticationBatch", map);
		return i;
	}


	@Override
	public List<InsurUserInfo> getAuthenticationBatchList(String idList) {
		return this.getSessionTemplate().selectList("getAuthenticationBatchList", idList);
	}


	@Override
	public InsurUserInfo findUserByOpenId(String openId) {
		return this.getSessionTemplate().selectOne("findUserByOpenId",openId);
	}


}
