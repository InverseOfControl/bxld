package com.ymkj.bxld.domain.user;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymkj.base.core.biz.entity.BaseEntity;





/**
* InsurUserInfo
* <p/>
* Author: 
* Date: 2017-12-09 14:25:35
* Mail: liangj@yuminsoft.com
*/
public class InsurUserInfo extends BaseEntity{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -287833754034268506L;

	private Integer id;
	
	private int pageNum; //当前页
    
    private int pageSize; //每页条数

    /**
    * 登陆账户
    */
    private String loginAccount;

    /**
    * 登陆密码
    */
    private String loginPwd;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 身份证
    */
    private String idCard;

    /**
    * 邀请码
    */
    private String inviteCode;

    /**
    * 区域
    */
    private String area;

    /**
    * 性别
    */
    private String sex;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 手机号码
    */
    private String phoneNo;

    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
    * 最后修改时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifyer;

    /**
    * 用户类型
    */
    private String userType;

    /**
    * 微信标识
    */
    private String openId;

    /**
    * 微信昵称
    */
    private String nickName;

    /**
    * 微信头像地址
    */
    private String headImgUrl;

    /**
    * 状态
    */
    private String status;

    /**
    * 备注
    */
    private String remark;
    
    /**
     * 认证状态
     */
    private String authenticationState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyer() {
		return lastModifyer;
	}

	public void setLastModifyer(Date lastModifyer) {
		this.lastModifyer = lastModifyer;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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
	

	public String getAuthenticationState() {
		return authenticationState;
	}

	public void setAuthenticationState(String authenticationState) {
		this.authenticationState = authenticationState;
	}

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
    
    

}