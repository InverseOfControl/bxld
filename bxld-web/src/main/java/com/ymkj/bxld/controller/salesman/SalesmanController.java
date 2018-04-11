package com.ymkj.bxld.controller.salesman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.service.user.IUserService;

@Controller
@RequestMapping(value = "/salesman")
public class SalesmanController extends BaseController{
	
	@Autowired
	private IUserService iuserService;

	
	@RequestMapping("")
	public String salesmanList() {
		return "salesman/salesman_list";
	}
	
	/**
	 * 认证分页查询
	 * @param page
	 * @param records
	 * @param insurUserInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listPage")
	public PageResponse<InsurUserInfo> salesmanList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "records", defaultValue = "10") int records,InsurUserInfo insurUserInfo) {
		insurUserInfo.setPageNum(page);
		insurUserInfo.setPageSize(records);
		return iuserService.getSalesmanListPage(insurUserInfo);
	}
	
	/**
	 * 批量认证
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/authenticationBatch",method = RequestMethod.POST)
	public Response<String> authenticationBatch(String ids) {
		String idList = "("+ids+")";
		Response<String> response = new Response<String>();
		response = iuserService.authenticationBatch(idList);
		return response;
	}
	
	/**
	 * 单个认证
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/authentication",method = RequestMethod.GET)
	public Response<String> authentication(Integer id) {
		Response<String> response = new Response<String>();
		response = iuserService.authentication(id);
		return response;
	}
	
	/**
	 * 注销
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancellation",method = RequestMethod.GET)
	public Response<String> cancellation(Integer id) {
		Response<String> response = new Response<String>();
		response = iuserService.cancellation(id);
		return response;
	}
	
}
