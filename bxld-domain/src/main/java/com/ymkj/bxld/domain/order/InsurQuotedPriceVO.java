package com.ymkj.bxld.domain.order;


import com.ymkj.base.core.biz.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* InsurQuotedPrice
* <p/>
* Author: 
* Date: 2017-12-12 15:59:48
* Mail: 
*/

public class InsurQuotedPriceVO extends BaseEntity {

    private Integer id;

    /**
    * 订单编号
    */
    private String orderCode;

    /**
    * 审核用户
    */
    private String auditingUserCode;

    /**
    * 保险公司编号
    */
    private String companyCode;

    /**
    * 保险公司名称
    */
    private String companyName;

    /**
    * 订单报价
    */
    private Long orderQuotedPrice;

    /**
    * 推荐指数
    */
    private String recommendGrade;

    /**
    * 交强险开始时间
    */
    private String ctaliStartTime;

    /**
    * 交强险截止时间
    */
    private String ctaliEndTime;

    /**
    * 商业险开始时间
    */
    private String businessStartTime;

    /**
    * 商业险截止时间
    */
    private String businessEndTime;

    /**
    * 报价状态
    */
    private String confirmStatus;

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
    * 报价图片附件ID
    */
    private String attachmentId;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAuditingUserCode() {
        return auditingUserCode;
    }

    public void setAuditingUserCode(String auditingUserCode) {
        this.auditingUserCode = auditingUserCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getOrderQuotedPrice() {
        return orderQuotedPrice;
    }

    public void setOrderQuotedPrice(Long orderQuotedPrice) {
        this.orderQuotedPrice = orderQuotedPrice;
    }

    public String getRecommendGrade() {
        return recommendGrade;
    }

    public void setRecommendGrade(String recommendGrade) {
        this.recommendGrade = recommendGrade;
    }

    public String getCtaliStartTime() {
        return ctaliStartTime;
    }

    public void setCtaliStartTime(String ctaliStartTime) {
        this.ctaliStartTime = ctaliStartTime;
    }

    public String getCtaliEndTime() {
        return ctaliEndTime;
    }

    public void setCtaliEndTime(String ctaliEndTime) {
        this.ctaliEndTime = ctaliEndTime;
    }

    public String getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
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