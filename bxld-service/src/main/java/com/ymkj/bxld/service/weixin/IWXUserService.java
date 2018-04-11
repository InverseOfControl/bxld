package com.ymkj.bxld.service.weixin;

import org.json.JSONObject;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.bxld.domain.user.InsurUserInfo;

public interface IWXUserService extends BaseService<InsurUserInfo>{
	
	/**
	 * description:用户是否认证
	 * autor:ym10159
	 * date:2017年12月14日 下午4:24:09
	 * @param userInfo 用户信息
	 */
	Response<Object> userIfAuth(InsurUserInfo userInfo);
	
	/**
	 * description:插入微信端用户认证信息
	 * autor:ym10159
	 * date:2017年12月14日 上午10:45:32
	 * @param userInfo 用户信息
	 * @return 保存结果返回
	 */
	Response<Object> saveWXUserInfo(InsurUserInfo userInfo,JSONObject obj);
}
