package com.ymkj.bxld.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注入system.properties配置文件
 * @author YM10159
 */
@Component
public class SystemProperties {
	/**
	 * 文件上传路径
	 */
	@Value("${sys.fileUploadPath}")
	private String fileUploadPath;
	/**
	 * 微信认证回调地址
	 */
	@Value("${sys.wxToAuth}")
	private String wxToAuth;
	/**
	 * 微信认证成功之后的跳转地址
	 */
	@Value("${sys.wxAuthTo}")
	private String wxAuthTo;
	/**
	 * 获取图片接口地址
	 */
	@Value("${sys.picPath}")
	private String picPath;
	

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public String getWxToAuth() {
		return wxToAuth;
	}

	public void setWxToAuth(String wxToAuth) {
		this.wxToAuth = wxToAuth;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getWxAuthTo() {
		return wxAuthTo;
	}

	public void setWxAuthTo(String wxAuthTo) {
		this.wxAuthTo = wxAuthTo;
	}
}
