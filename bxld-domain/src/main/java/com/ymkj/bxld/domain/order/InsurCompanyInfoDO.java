package com.ymkj.bxld.domain.order;


import com.ymkj.base.core.biz.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* InsurCompanyInfo
* <p/>
* Author: 
* Date: 2017-12-13 10:05:50
* Mail: 
*/

public class InsurCompanyInfoDO extends BaseEntity {

    private Integer id;

    /**
    * 保险公司编码
    */
    private String companyCode;

    /**
    * 保险公司简称
    */
    private String companyShortName;

    /**
    * 保险公司全称
    */
    private String companyFullName;

    /**
    * 保险公司电话
    */
    private String companyTelephone;

    /**
    * 保险公司地址
    */
    private String companyAddress;

    /**
    * 收款账户名称
    */
    private String payCardName;

    /**
    * 收款账户号
    */
    private String cardNumber;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPayCardName() {
        return payCardName;
    }

    public void setPayCardName(String payCardName) {
        this.payCardName = payCardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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