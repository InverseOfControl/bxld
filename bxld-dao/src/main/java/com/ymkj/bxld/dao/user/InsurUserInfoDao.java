package com.ymkj.bxld.dao.user;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.user.InsurUserInfo;

public interface InsurUserInfoDao extends BaseDao<InsurUserInfo>{


	List<InsurUserInfo> getUserListByLoginAccount(String loginAccount);

	int updateAuthenticationBatchList(String idList);

	List<InsurUserInfo> getAuthenticationBatchList(String idList);

	InsurUserInfo findUserByOpenId(String openId);

}
