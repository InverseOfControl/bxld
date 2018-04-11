package com.ymkj.bxld.controller.user;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.service.user.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController  extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService iuserService;
	
	@RequestMapping("")
	public String staffList() {
		return "user/insur_user_list";
	}
	
	
	/**
	 * 用户分页查询
	 * @param page
	 * @param records
	 * @param insurUserInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listPage")
	public PageResponse<InsurUserInfo> list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "records", defaultValue = "10") int records,InsurUserInfo insurUserInfo) {
//		String userName = Strings.isNotEmpty(request.getParameter("userName"))?request.getParameter("userName"):"";
//		String inviteCode = Strings.isNotEmpty(request.getParameter("inviteCode"))?request.getParameter("inviteCode"):"";
//		String nickName = Strings.isNotEmpty(request.getParameter("nickName"))?request.getParameter("nickName"):"";
//		Map<String, Object> map = new HashMap<>();
//		map.put("userName", userName);
//		map.put("inviteCode", inviteCode);
//		map.put("nickName", nickName);
//		List<InsurUserInfo> list = iuserService.getUserListPage(map);
		insurUserInfo.setPageNum(page);
		insurUserInfo.setPageSize(records);
		return iuserService.getUserListPage(insurUserInfo);
	}
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findUserById",method = RequestMethod.POST)
	public Response<InsurUserInfo> findUserById(Integer id) {
		Response<InsurUserInfo> response = new Response<InsurUserInfo>();
		response = iuserService.findUserById(id);		
		return response;
	}
	
	/**
	 * 保存或更新用户
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	public Response<String> saveUser(InsurUserInfo insurUserInfo) {
		Response<String> response = new Response<String>();
		response = iuserService.saveUser(insurUserInfo);
		return response;
	}
	
	
	/**
	 * 根据ID重置用户密码
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/modifPasswordById",method = RequestMethod.GET)
	public Response<String> modifPasswordById(Integer id){
		LOGGER.info("******重置密码:被重置密码的用户的id为:"+id);
		Response<String> response = new Response<String>();
		response = iuserService.modifPasswordById(id);
		return response;
	}
	
	/**
	 * 微信端个人信息查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/personalInfo")
    @ResponseBody
    public Response<InsurUserInfo> personalInfo(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String openId = request.getParameter("openId");
        return iuserService.queryPersonalInfo(openId);
    }
	

}
