package com.ymkj.bxld.service.user;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.domain.user.InsurUserInfo;

public interface IUserService {

	/**
	 * 用户分页查询
	 * @param insurUserInfo
	 * @return
	 */
	PageResponse<InsurUserInfo> getUserListPage(InsurUserInfo insurUserInfo);

	/**
	 * 根据ID获取用户
	 * @param id
	 * @return
	 */
	Response<InsurUserInfo> findUserById(Integer id);

	/**
	 * 保存或更新用户
	 * @param insurUserInfo
	 * @return
	 */
	Response<String> saveUser(InsurUserInfo insurUserInfo);

	/**
	 * 根据ID重置用户密码
	 * @param id
	 * @return
	 */
	Response<String> modifPasswordById(Integer id);

	/**
	 * 根据登录账号查询用户
	 * @param loginAccount
	 * @return
	 */
	List<InsurUserInfo> getUserListByLoginAccount(String loginAccount);

	/**
	 * 修改密码
	 * @param insurUserInfo
	 * @return
	 */
	Response<String> changePassword(InsurUserInfo insurUserInfo);
	
	/**
	 * 认证分页查询
	 * @param insurUserInfo
	 * @return
	 */
	PageResponse<InsurUserInfo> getSalesmanListPage(InsurUserInfo insurUserInfo);

	/**
	 * 批量认证
	 * @param idList
	 * @return
	 */
	Response<String> authenticationBatch(String idList);

	/**
	 * 认证
	 * @param id
	 * @return
	 */
	Response<String> authentication(Integer id);

	/**
	 * 注销
	 * @param id
	 * @return
	 */
	Response<String> cancellation(Integer id);
	
	/**
	 * 微信操作流程中的验证
	 * @param openId
	 * @return
	 */
	Response<InsurUserInfo> userValidation(String openId);

	/**
	 * 微信端查询个人信息
	 * @param openId
	 * @return
	 */
	Response<InsurUserInfo> queryPersonalInfo(String openId);
	
}
