package com.ymkj.bxld.domain.user;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
* InsurUserRole
* <p/>
* Author: 
* Date: 2017-12-15 13:23:06
* Mail: liangj@yuminsoft.com
*/

public class InsurUserRole extends BaseEntity {
    
  
	private static final long serialVersionUID = 2623696553755652650L;

	private Integer id;

    /**
    * 角色编号
    */
    private String roleCode;

    /**
    * 登陆账户
    */
    private String loginAccount;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
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