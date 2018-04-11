package com.ymkj.bxld.service.weixin.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.bxld.common.constants.Constants;
import com.ymkj.bxld.common.util.MD5Util;
import com.ymkj.bxld.dao.weixin.IWXUserInfoDao;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.service.weixin.IWXUserService;;

@Service
public class WXUserServiceImpl extends BaseServiceImpl<InsurUserInfo> implements IWXUserService{

	@Autowired
	private IWXUserInfoDao wxUserInfoDao;
	
	@Override
	protected BaseDao<InsurUserInfo> getDao() {
		return wxUserInfoDao;
	}
	
	@Override
	public Response<Object> userIfAuth(InsurUserInfo userInfo) {
		Response<Object> response = new Response<Object>();
		Map<String,Object> tmpMap = new HashMap<>();
		
		Map<String,Object> map = wxUserInfoDao.userIfAuth(userInfo);
		if(map == null || map.isEmpty()){
			tmpMap.put("authFlag", Constants.WXAuthRest.NO_AUTH.getCode());
			tmpMap.put("authMsg", "抱歉，您还未认证，请先认证！");
			response.setData(tmpMap); 
			return response;
		}
		String AuthState = ObjectUtils.toString(map.get("authentication_state"));
		if(AuthState.equals("0")){
			tmpMap.put("authFlag", Constants.WXAuthRest.AUTH_AUDIT.getCode());
			tmpMap.put("authMsg", "您的认证申请正在审核中！");
		}
		if(AuthState.equals("1")){
			tmpMap.put("authFlag", Constants.WXAuthRest.YS_AUTH.getCode());
			tmpMap.put("authMsg", "您的认证申请已通过！");
		}
		response.setData(tmpMap);
		return response;
	}

	
	@Override
	public Response<Object> saveWXUserInfo(InsurUserInfo userInfo,JSONObject obj) {
		Response<Object> response = new Response<Object>();
		Map<String,Object> tmpMap = new HashMap<>();
		
		if(obj.has("errcode")){
			tmpMap.put("authFlag", Constants.WXAuthRest.YS_AUTH.getCode());
			tmpMap.put("authMsg", "您还未关注微信公众号，请先关注！");
			response.setData(tmpMap);
			return response;
		}
		
		userInfo.setOpenId(userInfo.getOpenId());
		userInfo.setNickName(obj.getString("nickname"));
		userInfo.setSex(ObjectUtils.toString(obj.getInt("sex")));
		userInfo.setHeadImgUrl(obj.getString("headimgurl"));
		userInfo.setAuthenticationState("1");
		
		//插入微信信息
		userInfo.setLoginPwd(MD5Util.md5Hex("1234567".getBytes()));
		int count = wxUserInfoDao.saveWXUserInfo(userInfo);
		if(count > 0){
			tmpMap.put("authFlag", "success");
			tmpMap.put("authMsg", "感谢您提交申请信息，您的认证申请将在2个工作日内审核完成。");
		}else{
			tmpMap.put("authFlag", "fail");
			tmpMap.put("authMsg", "保存失败，请联系系统管理员！");
		}
		response.setData(tmpMap);
		return response;
	}
	
}
