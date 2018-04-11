package com.ymkj.bxld.common.constants;

public class InsurUserEnum {

	 public enum AuthenticationState{

		UNCERTIFIED("0", "未认证"),
		AUTHENTICATED("1", "已认证"),
		CANCELLATION("2", "已注销");
    	

    	AuthenticationState(String code, String value){
    		this.code = code;
    		this.value = value;
    	}

    	private String code;

    	private String value;

    	public String getCode(){
    		return code;
    	}

    	public String getValue(){
    		return value;
    	}
	}
	
}
