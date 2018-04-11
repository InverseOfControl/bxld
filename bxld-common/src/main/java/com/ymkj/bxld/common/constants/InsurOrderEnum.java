package com.ymkj.bxld.common.constants;

/**
 * @Author: lihhuimeng
 * @description: 订单枚举类
 * @date: 2017/12/9 15:17
 */
public class InsurOrderEnum {

    /**
     * 订单操作类型
     * lihuimeng
     */
    public enum OptType{

        ORDER_PATCH("order_Patch", "补件"),
        ORDER_CHECK("order_check", "审核"),
        ORDER_EXPORT("order_export", "导出"),
        ORDER_OFFER("order_offer", "订单报价"),
        ORDER_PAYMENT("order_payment", "上传付款信息"),
        ORDER_CONFIRM("order_confirm", "付款确认");

        OptType(String code, String value){
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
     * 订单状态
     * lihuimeng
     */
    public enum OrderStatus{

        SHZ_DSP("SHZ-DSP", "审核中-待审批"),
        SHZ_DBJ("SHZ-DBJ", "审核中-待补件"),
        SHZ_YBJ("SHZ-YBJ", "审核中-已补件"),
        SHZ_YSH("SHZ-YSH", "已审核-已审核"),
        YSH_YDC("YSH-YDC", "已审核-已导出"),
        YSH_YBJ("YSH-YBJ", "已审核-已报价"),
        DFK_DFK("DFK-DFK", "待付款-待付款"),
        DFK_YQR("DFK-YQR", "待付款-已确认"),
        YFK_DFK("YFK-DFK", "已付款-代付款"),
        YFK_YFK("YFK-YFK", "已付款-已付款"),
        YCX_YGB("YCX-YGB", "已撤销-已关闭");

        OrderStatus(String code, String value){
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
     * 审核结果
     * lihuimeng
     */
    public enum CheckResult{

        PASS("pass", "审核通过"),
        REJECT("reject", "审核拒绝");

        CheckResult(String code, String value){
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
     * 状态
     * lihuimeng
     */
    public enum Status{

        enable("0", "可用"),
        disable("1", "禁用"),
        delete("2", "删除");

        Status(String code, String value){
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
     * 保险公司
     * lihuimeng
     */
    public enum InsurCompany{

        TABX("TABX", "天安保险"),
        TPBX("TPBX", "太平保险"),
        ZGRB("ZGRB", "中国人保"),
        ZGRS("ZGRS", "中国人寿"),
        ZGPA("ZGPA", "中国平安");

        InsurCompany(String code, String value){
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
     * 报价状态
     * lihuimeng
     */
    public enum ConfirmStatus{

        DGM("DGM", "待购买"),
        YGM("YGM", "已购买");

        ConfirmStatus(String code, String value){
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
     * 补件备注枚举
     * lihuimeng
     */
    public enum PatchRemark {

        MATERIAL_LOSS("material_loss", "材料缺失"),
        PICTURE_DIM("picture_dim", "照片看不清楚");

        PatchRemark(String code, String value){
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
     * 保险产品
     * lihuimeng
     */
    public enum Product{
        PRODUCT_CLSUX("PRODUCT_CLSUX", "车辆损失险"),
        PRODUCT_DSZZRX("PRODUCT_DSZZRX", "第三者责任险"),
        PRODUCT_SJZRX("PRODUCT_SJZRX", "司机责任险"),
        PRODUCT_CKZRX("PRODUCT_CKZRX", "乘客责任险"),
        PRODUCT_DQX("PRODUCT_DQX", "全车盗抢险"),
        PRODUCT_ZRSSX("PRODUCT_ZRSSX", "自燃损失险"),
        PRODUCT_CSHHX("PRODUCT_CSHHX", "车身划痕险"),
        PRODUCT_SSX("PRODUCT_SSX", "涉水险"),
        PRODUCT_PLPSX("PRODUCT_BLPSX", "玻璃破碎险"),
        PRODUCT_ZBDDSF("PRODUCT_ZBDDSF", "车损无法找到第三方"),
        PRODUCT_ZDZXC("PRODUCT_ZDZXC", "指定专修厂"),
        PRODUCT_JQXCHS("PRODUCT_JQXCHS", "交强险/车船税");

        Product(String code, String value){
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


