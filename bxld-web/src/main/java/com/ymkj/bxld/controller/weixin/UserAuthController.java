package com.ymkj.bxld.controller.weixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.common.constants.Constants;
import com.ymkj.bxld.config.SystemProperties;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.service.weixin.IWXUserService;

/**
 * 微信公众号用户信息获取
 * @author YM10159
 */
@Controller
@RequestMapping("weixin")
public class UserAuthController extends BaseController{

	private static final Logger logger = Logger.getLogger(UserAuthController.class);
	
	@Autowired
	private IWXUserService wxUserService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private SystemProperties systemProperties; 
	
	@RequestMapping(value = "/userIfAuth")
	@ResponseBody
	public Response<Object> userIfAuth(InsurUserInfo userInfo) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return wxUserService.userIfAuth(userInfo);
	}
	
	/**
	 * description:通过openId获取公众号用户信息
	 * autor:ym10159
	 * date:2017年12月17日 下午4:27:32
	 * @param userInfo 用户信息
	 */
	@RequestMapping(value = "/userAuth")
	@ResponseBody
	public Response<Object> userAuth(InsurUserInfo userInfo) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		logger.info("微信用户认证，openId="+userInfo.getOpenId());
		//获取微信端用户信息
		ApiResult ar = UserApi.getUserInfo(userInfo.getOpenId());
		logger.info("微信用户认证信息："+ar.getJson());
		
		return wxUserService.saveWXUserInfo(userInfo,new JSONObject(ar.getJson()));
	}
	
	/**
	 * description:跳转到微信授权页面
	 * autor:ym10159
	 * date:2017年12月17日 下午4:24:38
	 */
	@RequestMapping(value = "/toOauth")
	public void toOauth() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String appId = PropKit.get("appId");
		String redirectUri = systemProperties.getWxToAuth();
		String authUri = SnsAccessTokenApi.getAuthorizeURL(appId, redirectUri, true);
		request.setAttribute("authUri", authUri);
	}
	
	/**
	 * description:认证
	 * autor:ym10159
	 * date:2017年12月17日 下午4:26:21
	 * @return 公众号openId
	 */
	@RequestMapping(value = "/oauth")
	public void oauth() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String code=request.getParameter("code");
		String appId= PropKit.get("appId");
		String secret = PropKit.get("appSecret");
		SnsAccessToken  SnsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, secret, code);
		System.out.println(SnsAccessToken.getOpenid());
		try {
			response.sendRedirect(systemProperties.getWxAuthTo()+"?openId="+SnsAccessToken.getOpenid());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
