package com.ymkj.bxld.service.user.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.bxld.common.constants.Constants;
import com.ymkj.bxld.common.constants.InsurUserEnum;
import com.ymkj.bxld.common.exception.BizErrorCode;
import com.ymkj.bxld.common.util.MD5Util;
import com.ymkj.bxld.common.util.Strings;
import com.ymkj.bxld.dao.user.InsurUserInfoDao;
import com.ymkj.bxld.dao.user.InsurUserRoleDao;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.domain.user.InsurUserRole;
import com.ymkj.bxld.service.user.IUserService;

@Service
public class IUserServiceImpl implements IUserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IUserServiceImpl.class);

	@Autowired
	private InsurUserInfoDao insurUserInfoDao;
	
	@Autowired
	private InsurUserRoleDao insurUserRoleDao;
	
	@Autowired
	private IUserService iUserService;

	public PageResponse<InsurUserInfo> getUserListPage(InsurUserInfo insurUserInfo) {
		PageResponse<InsurUserInfo> response = new PageResponse<InsurUserInfo>();
		
		PageParam pageParam = new PageParam(insurUserInfo.getPageNum(), insurUserInfo.getPageSize());
		Map<String,Object> paramMap = null;
		
		//实体对象转成请求map
		try {
			paramMap = BeanKit.bean2map(insurUserInfo);
		} catch (Exception e) {
		}
		PageBean<InsurUserInfo> pageBean = insurUserInfoDao.listPage(pageParam, paramMap);
		List<InsurUserInfo> pageList = pageBean.getRecords();
		
		response.setRecords(pageList);
		//复制分页信息到响应对象
		BeanUtils.copyProperties(pageBean, response, "records");
		return response;
	}

	@Override
	public Response<InsurUserInfo> findUserById(Integer id) {	
		Response<InsurUserInfo> response = new Response<InsurUserInfo>();
		InsurUserInfo insurUserInfo = insurUserInfoDao.getById(id);
		if(null!=insurUserInfo){
			response.setData(insurUserInfo);
		}else{
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("系统异常,请稍后重试");
		}
		return response;
	}

	@Override
	public Response<String> saveUser(InsurUserInfo insurUserInfo) {
		Response<String> response = new Response<String>();
		if(null==insurUserInfo.getId()){	
			if(Strings.isNotEmpty(insurUserInfo.getLoginAccount())){				
				List<InsurUserInfo> list= insurUserInfoDao.getUserListByLoginAccount(insurUserInfo.getLoginAccount());
				if(list.size()>=0){
					response.setRepCode(BizErrorCode.EOERR.getErrorCode());
		            response.setRepMsg("登陆账户已存在");
		            return response;
				}
			}
			String password="";
			try {
				password = MD5Util.md5Hex(MD5Util.md5Hex("12345678"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			insurUserInfo.setLoginPwd(password);
			insurUserInfo.setStatus(Constants.DATA_VALID);
			insurUserInfo.setCreateTime(new Date());
			Long i = insurUserInfoDao.insert(insurUserInfo);
			if(i>0){		
				response.setRepMsg("用户新增成功");
			}else{
				response.setRepCode(BizErrorCode.EOERR.getErrorCode());
				LOGGER.info("用户新增失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
	            response.setRepMsg("用户新增失败");
			}
			return response;
		}
		Long j = insurUserInfoDao.update(insurUserInfo);
		if(j>0){		
			response.setRepMsg("用户修改成功");
		}else{
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("用户修改失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("用户修改失败");
		}
		return response;
	}

	@Override
	public Response<String> modifPasswordById(Integer id){
		Response<String> response = new Response<String>();
		InsurUserInfo insurUserInfo = insurUserInfoDao.getById(id);
		if(null == insurUserInfo){
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("密码重置失败,对应id的用户不存在,id为:"+id);
            response.setRepMsg("对应id的用户不存在");
		}
		String password = "";
		try {
			password = MD5Util.md5Hex(MD5Util.md5Hex("12345678"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insurUserInfo.setLoginPwd(password);
		long i = insurUserInfoDao.update(insurUserInfo);
		if(i>0){		
			response.setRepMsg("密码重置成功");
		}else{
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("密码重置失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("密码重置失败");
		}
		return response;
	}

	@Override
	public List<InsurUserInfo> getUserListByLoginAccount(String loginAccount) {
		return insurUserInfoDao.getUserListByLoginAccount(loginAccount);
	}

	@Override
	public Response<String> changePassword(InsurUserInfo insurUserInfo) {
		Response<String> response = new Response<String>();
		long i = insurUserInfoDao.update(insurUserInfo);
		if(i>0){		
			response.setRepMsg("密码修改成功");
		}else{
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("密码修改失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("密码修改失败");
		}
		return response;
	}
	
	@Override
	public PageResponse<InsurUserInfo> getSalesmanListPage(InsurUserInfo insurUserInfo) {
		PageResponse<InsurUserInfo> response = new PageResponse<InsurUserInfo>();		
		PageParam pageParam = new PageParam(insurUserInfo.getPageNum(), insurUserInfo.getPageSize());
		Map<String,Object> paramMap = null;
		
		//实体对象转成请求map
		try {
			paramMap = BeanKit.bean2map(insurUserInfo);
		} catch (Exception e) {
		}
		PageBean<InsurUserInfo> pageBean = insurUserInfoDao.listPage(pageParam, paramMap, "getSalesmanList", "getSalesmanListCount");
		List<InsurUserInfo> pageList = pageBean.getRecords();
		
		response.setRecords(pageList);
		//复制分页信息到响应对象
		BeanUtils.copyProperties(pageBean, response, "records");
		return response;
	}

	@Override
	@Transactional
	public Response<String> authenticationBatch(String idList) {
		Response<String> response = new  Response<String>();
		List<InsurUserInfo> list = insurUserInfoDao.getAuthenticationBatchList(idList);
		if(list.size()==0){
			response.setRepMsg("操作成功");
		}else{
			for(InsurUserInfo userInfo:list){				
				userInfo.setAuthenticationState(InsurUserEnum.AuthenticationState.AUTHENTICATED.getCode());
				insurUserInfoDao.update(userInfo);
				InsurUserRole insurUserRole = new InsurUserRole();
				insurUserRole.setLoginAccount(userInfo.getLoginAccount());
				insurUserRole.setRoleCode("S");
				insurUserRole.setStatus(Constants.DATA_VALID);
				insurUserRole.setCreateTime(new Date());
				Long i = insurUserRoleDao.insert(insurUserRole);
				if(i<0){		
					response.setRepCode(BizErrorCode.EOERR.getErrorCode());
					LOGGER.info("批量认证失败,用户id集合为:"+idList);
		            response.setRepMsg("操作失败");
		            return response;
				}
			}
		}		
		response.setRepMsg("操作成功");		
		return response;
	}

	@Override
	@Transactional
	public Response<String> authentication(Integer id) {
		Response<String> response = new Response<String>();
		InsurUserInfo insurUserInfo = insurUserInfoDao.getById(id);
		if(null == insurUserInfo){
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("认证失败,对应id的用户不存在,id为:"+id);
            response.setRepMsg("对应id的用户不存在");
		}
		insurUserInfo.setAuthenticationState(InsurUserEnum.AuthenticationState.AUTHENTICATED.getCode());
		
		long i = insurUserInfoDao.update(insurUserInfo);
		if(i<0){		
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("认证失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("认证失败");
            return response;
		}
		InsurUserRole insurUserRole = new InsurUserRole();
		insurUserRole.setLoginAccount(insurUserInfo.getLoginAccount());
		insurUserRole.setRoleCode("S");
		insurUserRole.setStatus(Constants.DATA_VALID);
		insurUserRole.setCreateTime(new Date());
		Long j = insurUserRoleDao.insert(insurUserRole);
		if(j<0){		
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("认证失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("认证失败");
            return response;
		}
		response.setRepMsg("认证成功");
		return response;
	}

	@Override
	@Transactional
	public Response<String> cancellation(Integer id) {
		Response<String> response = new Response<String>();
		InsurUserInfo insurUserInfo = insurUserInfoDao.getById(id);
		if(null == insurUserInfo){
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("注销失败,对应id的用户不存在,id为:"+id);
            response.setRepMsg("对应id的用户不存在");
		}
		insurUserInfo.setAuthenticationState(InsurUserEnum.AuthenticationState.CANCELLATION.getCode());
		insurUserInfo.setRemark(insurUserInfo.getOpenId());
		insurUserInfo.setOpenId(null);
		long i = insurUserInfoDao.update(insurUserInfo);
		if(i<0){		
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("注销失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("注销失败");
            return response;
		}
		int j = insurUserRoleDao.deleteByLoginAccount(insurUserInfo.getLoginAccount());
		if(j>0){		
			response.setRepMsg("注销成功");
		}else{
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			LOGGER.info("注销失败,用户登录账户为:"+insurUserInfo.getLoginAccount());
            response.setRepMsg("注销失败");
		}
		return response;
	}

	@Override
	public Response<InsurUserInfo> userValidation(String openId) {
		Response<InsurUserInfo> response = new Response<InsurUserInfo>();
		InsurUserInfo insurUserInfo = insurUserInfoDao.findUserByOpenId(openId);
		if(null == insurUserInfo){
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
			response.setRepMsg("用户为认证");
		}else{			
			response.setRepCode(BizErrorCode.SUCCESS.getErrorCode());
			response.setData(insurUserInfo);
		}
		return response;
	}

	@Override
	public Response<InsurUserInfo> queryPersonalInfo(String openId) {	
		return iUserService.userValidation(openId);
	}

}

