package com.ymkj.bxld.domain.order;

import com.ymkj.base.core.biz.entity.BaseEntity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * InsurOrderHistory
 * <p/>
 * Author: lihuimeng
 * Date: 2017-12-11 15:35:04
 * Mail:
 */


public class InsurOrderHistory extends BaseEntity {

    private Integer id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 操作类型
     */
    private String optType;

    /**
     * 操作人账号
     */
    private String auditingLoginUser;

    /**
     * 操作人姓名
     */
    private String auditingUserName;

    /**
     * 订单状态
     */
    private String auditingStatus;

    /**
     * 操作结果
     */
    private String auditingResult;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getAuditingLoginUser() {
        return auditingLoginUser;
    }

    public void setAuditingLoginUser(String auditingLoginUser) {
        this.auditingLoginUser = auditingLoginUser;
    }

    public String getAuditingUserName() {
        return auditingUserName;
    }

    public void setAuditingUserName(String auditingUserName) {
        this.auditingUserName = auditingUserName;
    }

    public String getAuditingStatus() {
        return auditingStatus;
    }

    public void setAuditingStatus(String auditingStatus) {
        this.auditingStatus = auditingStatus;
    }

    public String getAuditingResult() {
        return auditingResult;
    }

    public void setAuditingResult(String auditingResult) {
        this.auditingResult = auditingResult;
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