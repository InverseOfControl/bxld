package com.ymkj.bxld.domain.log;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
* InsurLog
* <p/>
* Author: 
* Date: 2017-12-15 13:06:29
* Mail: liangj@yuminsoft.com
*/

public class InsurLogDO extends BaseEntity {

   
	private static final long serialVersionUID = -6174862599939596169L;

	private Integer id;

    /**
    * 操作用户编号
    */
    private String optUserAccount;

    /**
    * 操作人姓名
    */
    private String optUserName;

    /**
    * 操作时间
    */
    private String optTime;

    /**
    * 操作类型
    */
    private String optType;

    /**
    * 操作数据编号
    */
    private String optDataCode;

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

	public String getOptUserAccount() {
		return optUserAccount;
	}

	public void setOptUserAccount(String optUserAccount) {
		this.optUserAccount = optUserAccount;
	}

	public String getOptUserName() {
		return optUserName;
	}

	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getOptDataCode() {
		return optDataCode;
	}

	public void setOptDataCode(String optDataCode) {
		this.optDataCode = optDataCode;
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