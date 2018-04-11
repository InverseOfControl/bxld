package com.ymkj.bxld.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.bxld.common.constants.InsurOrderEnum;
import com.ymkj.bxld.common.util.EnumerationUtils;
import com.ymkj.bxld.domain.attachment.AttchmentVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:14
 */
public class InsurOrderVO extends BaseEntity{


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
     * 录单用户名称
     */
    private String applyUserName; 

    /**
     * 保险公司编号
     */
    private String companyCode;

    /**
     * 保险公司名称
     */
    private String companyName;

    /**
     * 车主类型(个人："GR", 企业："QY")
     */
    private String consumerType;

    /**
     * 车主类型名称
     */
    private String consumerTypeText;

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
     * 订单状态文本
     */
    private String orderStatusText;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态。0：可用 1：禁用 2：删除
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单查询开始日期
     */
    private Date queryStartDate;

    /**
     * 订单查询结束日期
     */
    private Date queryEndDate;

    /**
     * 保险公司简称
     */
    private String companyShortName;

    /**
     * 保险公司全称
     */
    private String companyFullName;

    /**

     * 收款账户名称
     */
    private String payCardName;

    /**
     * 收款账户号
     */
    private String cardNumber;

    /**
     * 订单金额
     */
    private Long orderQuotedPrice;
    
    /**
     * 申请进度
     */
    private String applicationProgress;

    /**
     * 补件信息
     */
    private String makeupFile;

    private int pageNum; //当前页

    private int pageSize; //每页条数

    /**
     * 证件附件信息集合
     */
    private List<AttchmentVO> idAttchmentList;

    /**
     * 付款附件信息集合
     */
    private List<AttchmentVO> payAttchmentList;

    /**
     * 报价附件信息集合
     */
    private List<AttchmentVO> offerAttchmentList;

    /**
     * 支付反馈附件信息集合
     */
    private List<AttchmentVO> payBackAttchmentList;


    /**
     * 订单补件信息备注
     */
    private List<AttchmentVO> patchRemark;

    @JsonIgnore
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    @JsonIgnore
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getConsumerTypeText() {
        if ("GR".equals(this.consumerType)) {
            return "个人";
        }
        if ("QY".equals(this.consumerType)) {
            return "企业";
        }
        return null;
    }

    public void setConsumerTypeText(String consumerTypeText) {
        this.consumerTypeText = consumerTypeText;
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

    public String getApplyUserAccount() {
        return applyUserAccount;
    }

    public void setApplyUserAccount(String applyUserAccount) {
        this.applyUserAccount = applyUserAccount;
    }  

    public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public Date getQueryStartDate() {
        return queryStartDate;
    }

    public void setQueryStartDate(Date queryStartDate) {
        this.queryStartDate = queryStartDate;
    }

    public Date getQueryEndDate() {
        return queryEndDate;
    }

    public void setQueryEndDate(Date queryEndDate) {
        this.queryEndDate = queryEndDate;
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

    public Long getOrderQuotedPrice() {
        return orderQuotedPrice;
    }

    public void setOrderQuotedPrice(Long orderQuotedPrice) {
        this.orderQuotedPrice = orderQuotedPrice;
    }

    public String getMakeupFile() {
        return makeupFile;
    }

    public void setMakeupFile(String makeupFile) {
        this.makeupFile = makeupFile;
    }

    public String getOrderStatusText() {
        return EnumerationUtils.getValueByCode(InsurOrderEnum.OrderStatus.class, this.orderStatus);
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

	public String getApplicationProgress() {
		return applicationProgress;
	}

	public void setApplicationProgress(String applicationProgress) {
		this.applicationProgress = applicationProgress;
	}

    public List<AttchmentVO> getIdAttchmentList() {
        return idAttchmentList;
    }

    public void setIdAttchmentList(List<AttchmentVO> idAttchmentList) {
        this.idAttchmentList = idAttchmentList;
    }

    public List<AttchmentVO> getPayAttchmentList() {
        return payAttchmentList;
    }

    public void setPayAttchmentList(List<AttchmentVO> payAttchmentList) {
        this.payAttchmentList = payAttchmentList;
    }

    public List<AttchmentVO> getPatchRemark() {
        return patchRemark;
    }

    public void setPatchRemark(List<AttchmentVO> patchRemark) {
        this.patchRemark = patchRemark;
    }

    public List<AttchmentVO> getOfferAttchmentList() {
        return offerAttchmentList;
    }

    public void setOfferAttchmentList(List<AttchmentVO> offerAttchmentList) {
        this.offerAttchmentList = offerAttchmentList;
    }

    public List<AttchmentVO> getPayBackAttchmentList() {
        return payBackAttchmentList;
    }

    public void setPayBackAttchmentList(List<AttchmentVO> payBackAttchmentList) {
        this.payBackAttchmentList = payBackAttchmentList;
    }
}
