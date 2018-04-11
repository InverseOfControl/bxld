package com.ymkj.bxld.common.exception;

/**
 * demo 异常枚举, 可自行定义(非核心包中的错误类型), 须遵守规范 ( CoreErrorCode 提供常见的校验类型)
 */
public enum BizErrorCode {
	SUCCESS("000000","接口调用成功"),
	EOERR("999999","错误，错误:[{0}]");
	
	private String code;

	private String defaultMessage;

	BizErrorCode(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public String getErrorCode() {
		return this.code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

}
