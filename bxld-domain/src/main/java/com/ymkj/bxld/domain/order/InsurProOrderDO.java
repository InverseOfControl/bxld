package com.ymkj.bxld.domain.order;


import com.ymkj.base.core.biz.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @discripeion 订单产品关系实体
 * @Author lihuimeng
 * @date 2017/12/17 15:03
 *
 */
public class InsurProOrderDO extends BaseEntity {

    private Integer id;

    /**
    * 订单编号
    */
    private String orderCode;

    /**
    * 险别编号
    */
    private String productCode;

    /**
    * 险别名称
    */
    private String productName;

    /**
    * 是否不计免赔
    */
    private String ifAbatement;

    /**
    * 投保金额
    */
    private Long insurePrice;

    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

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

    public String getIfAbatement() {
        return ifAbatement;
    }

    public void setIfAbatement(String ifAbatement) {
        this.ifAbatement = ifAbatement;
    }

    public Long getInsurePrice() {
        return insurePrice;
    }

    public void setInsurePrice(Long insurePrice) {
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