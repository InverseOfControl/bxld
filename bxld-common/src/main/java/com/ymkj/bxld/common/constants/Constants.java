package com.ymkj.bxld.common.constants;

public class Constants {
	
	public static final String SYS_LOGIN_USR = "loginIUser";// 当前登录用户
	
	/**
	 * 数据有效性(0:有效, 1：无效)
	 */
	public static final String DATA_VALID = "0";
	public static final String DATA_UNVALID = "1";
	
	/**
	 * 员工角色
	 */
	public static final String SALE_EMP = "salesman";
	public static final String MANGER_EMP = "management";

	/**
	 * 图片所属环节
	 * @author YM10159
	 */
    public enum picLink{
        APPLY_LINK("SQ", "申请"),
        OFFER_LINK("BJ", "报价"),
        PAY_LINK("ZF", "支付"),
        PAY_BACK("FK", "支付反馈");

    	picLink(String code, String value){
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
    
    /**
	 * 微信用户认证结果
	 * @author YM10159
	 */
    public enum WXAuthRest{
    	NO_AUTH("0", "未认证"),
    	YS_AUTH("1", "已认证"),
    	AUTH_AUDIT("3", "审核中");

    	WXAuthRest(String code, String value){
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
