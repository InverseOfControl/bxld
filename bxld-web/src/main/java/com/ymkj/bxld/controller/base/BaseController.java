package com.ymkj.bxld.controller.base;

import com.ymkj.bxld.common.editor.DateEditor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.ymkj.base.core.biz.api.message.Response;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

public class BaseController{

	protected static Log logger = LogFactory.getLog(BaseController.class);

	/**
	 * 转JSON字符串
	 * 
	 * @param responseInfo
	 * @return
	 */
	public static String toResponseJSON(Response<?> responseInfo) {
		if (responseInfo == null) {
			logger.warn("ResponseInfoToJSONString Method Param IS NULL");
			return "{}";
		}
		String jsonStr = JSONObject.toJSONString(responseInfo);
		return jsonStr;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

}
