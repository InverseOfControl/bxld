package com.ymkj.bxld.domain.order;


import com.ymkj.base.core.biz.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @discripeion 保险产品实体vo
 * @Author lihuimeng
 * @date 2017/12/17 13:31
 *
 */
public class InsurProductVO extends BaseEntity {

    private Integer id;

    /**
    * 产品编码,保险名称首字母大写
    */
    private String productCode;

    /**
    * 产品名称
    */
    private String productName;

    /**
    * 产品类型编码,JBX:基本险 SYX:商业险
    */
    private String productTypeCode;

    /**
    * 产品类型名称
    */
    private String productTypeName;

    /**
    * 是否有不计免赔,只用于显示，不代表选择结果
    */
    private String productAbatement;

    /**
    * 投保金额,用英文逗号隔开
    */
    private String insurePrice;

    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
    * 状态。0：可用 1：禁用 2：删除
    */
    private String status;

    /**
    * 备注
    */
    private String remark;

    /**
     * 是否不计免赔
     */
    private String ifAbatement;

    public String getIfAbatement() {
        return ifAbatement;
    }

    public void setIfAbatement(String ifAbatement) {
        this.ifAbatement = ifAbatement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductAbatement() {
        return productAbatement;
    }

    public void setProductAbatement(String productAbatement) {
        this.productAbatement = productAbatement;
    }

    public String getInsurePrice() {
        return insurePrice;
    }

    public void setInsurePrice(String insurePrice) {
        this.insurePrice = insurePrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}