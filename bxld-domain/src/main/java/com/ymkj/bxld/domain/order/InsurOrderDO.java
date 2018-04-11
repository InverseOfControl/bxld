package com.ymkj.bxld.domain.order;

import com.ymkj.base.core.biz.entity.BaseEntity;

import java.util.Date;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:12
 */
public class InsurOrderDO extends BaseEntity {


    private Integer id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 录单用户
     */
    private String applyUserAccount;

    /**
     * 保险公司编号
     */
    private String companyCode;

    /**
     * 保险公司名称
     */
    private String companyName;

    /**
     * 车主类型
     */
    private String consumerType;

    /**
     * 承保城市
     */
    private String contractCity;

    /**
     * 车主姓名
     */
    private String consumerName;

    /**
     * 车主身份证
     */
    private String consumerIdCard;

    /**
     * 营业执照名称
     */
    private String enterpriseName;

    /**
     * 营业执照编号
     */
    private String enterpriseLicence;

    /**
     * 车主手机号
     */
    private String consumerPhone;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 保单寄送地址
     */
    private String receiveAddress;

    /**
     * 车牌号码
     */
    private String plateNumber;

    /**
     * 上传附件数
     */
    private Integer attachmentNum;

    /**
     * 申请时间
     */
    private String applyTime;

    /**
     * 审核时间
     */
    private String auditingTime;

    /**
     * 签约时间
     */
    private String signTime;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 状态。0：可用 1：禁用 2：删除
     */
    private String status;

    /**
     * 补件信息
     */
    private String makeupFile;

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

    public String getApplyUserAccount() {
        return applyUserAccount;
    }

    public void setApplyUserAccount(String applyUserAccount) {
        this.applyUserAccount = applyUserAccount;
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

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public String getContractCity() {
        return contractCity;
    }

    public void setContractCity(String contractCity) {
        this.contractCity = contractCity;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerIdCard() {
        return consumerIdCard;
    }

    public void setConsumerIdCard(String consumerIdCard) {
        this.consumerIdCard = consumerIdCard;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseLicence() {
        return enterpriseLicence;
    }

    public void setEnterpriseLicence(String enterpriseLicence) {
        this.enterpriseLicence = enterpriseLicence;
    }

    public String getConsumerPhone() {
        return consumerPhone;
    }

    public void setConsumerPhone(String consumerPhone) {
        this.consumerPhone = consumerPhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getAttachmentNum() {
        return attachmentNum;
    }

    public void setAttachmentNum(Integer attachmentNum) {
        this.attachmentNum = attachmentNum;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditingTime() {
        return auditingTime;
    }

    public void setAuditingTime(String auditingTime) {
        this.auditingTime = auditingTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getMakeupFile() {
        return makeupFile;
    }

    public void setMakeupFile(String makeupFile) {
        this.makeupFile = makeupFile;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
