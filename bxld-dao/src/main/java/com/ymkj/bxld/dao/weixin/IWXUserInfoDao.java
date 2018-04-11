package com.ymkj.bxld.dao.weixin;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.user.InsurUserInfo;

public interface IWXUserInfoDao extends BaseDao<InsurUserInfo>{
	
	/**
	 * description:用户是否认证
	 * autor:ym10159
	 * date:2017年12月14日 下午4:20:35
	 * @return 用户信息
	 */
	Map<String,Object> userIfAuth(InsurUserInfo userInfo);
	
	/**
	 * description:认证用户是否是公司内部员工
	 * autor:ym10159
	 * date:2017年12月14日 下午5:04:04
	 * @param userInfo 用户信息
	 */
	int userIfInCompany(InsurUserInfo userInfo);
	
	
	/**
	 * description:保存微信用户认证信息
	 * autor:ym10159
	 * date:2017年12月14日 下午3:14:52
	 * @param userInfo 用户信息
	 */
	int saveWXUserInfo(InsurUserInfo userInfo);
}
