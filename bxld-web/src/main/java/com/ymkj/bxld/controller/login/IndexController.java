package com.ymkj.bxld.controller.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.common.constants.Constants;
import com.ymkj.bxld.common.exception.BizErrorCode;
import com.ymkj.bxld.common.util.MD5Util;
import com.ymkj.bxld.common.util.Strings;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.service.user.IUserService;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private IUserService iuserService;

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping("logout")
	public String logout(Model model, ServletRequest request,HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(value = "index/leftMenu", method = RequestMethod.GET)
	public String leftMenu() {
		return "common/leftMenu";
	}

	@RequestMapping("dataGrid")
	public String dataGrid() {
		return "datagrid";
	}

	@RequestMapping(value = "index/findMenuTree")
	@ResponseBody
	public Object findMenuTree() {
		InputStream is = getClass().getResourceAsStream("/leftMenu.json");
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {}
		
		StringBuffer sb = new StringBuffer();
		String lineStr = "";
		try {
			while ((lineStr = br.readLine()) != null)
				sb.append(lineStr.replaceAll("\\s", ""));
		} catch (Exception e) {
		}finally {
			try {
				is.close();
				br.close();
			} catch (IOException e) {}
		}
		
		return sb.toString();
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		InsurUserInfo insurUserInfo = new InsurUserInfo();
		String loginAccount = request.getParameter("loginAccount");
		String password = request.getParameter("password");
		String validCode = request.getParameter("validCode");
		HttpSession session = request.getSession();
		if(Strings.isEmpty(loginAccount)){
			model.addAttribute("msg", "请输入用户名");
			return "login";
		}
		if(Strings.isEmpty(password)){
			model.addAttribute("msg", "请输入密码");
			return "login";
		}
//		if(Strings.isEmpty(validCode)){
//			model.addAttribute("msg", "请输入验证码");
//			return "login";
//		}
//		if(!validCode.equalsIgnoreCase(String.valueOf(session.getAttribute("vnum")))){
//			model.addAttribute("msg", "验证码错误");
//			return "login";
//		}
		try {
			password = MD5Util.md5Hex(MD5Util.md5Hex(password));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insurUserInfo.setLoginAccount(loginAccount);
		List<InsurUserInfo> list = new ArrayList<InsurUserInfo>();
		list = iuserService.getUserListByLoginAccount(loginAccount);
		if(list.isEmpty()){
			model.addAttribute("msg", "用户名错误请重试!");
			return "login";
		}else{
			insurUserInfo = list.get(0);
				String userpass = insurUserInfo.getLoginPwd();
				if(password.equals(userpass)){
					
					session.setAttribute(Constants.SYS_LOGIN_USR,insurUserInfo);
					return "redirect:/";
				}else{
					model.addAttribute("msg", "密码错误请重试!");
					return "login";
				}
		}
		
		
		
	}
	
	/**
	 * 验证码
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/verifyCodeImage")
	public String verifyCodeImage(HttpServletRequest req, HttpServletResponse  res){
		System.out.println("验证码");
		return "verifyCodeImage";
	}
	

	/**
	 * 修改密码
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public Response<String> changePassword(HttpServletRequest req, HttpServletResponse  res){
		Response<String> response = new Response<>();
		String oldPassword = req.getParameter("oldPassword");//旧密码
		String newPassword = req.getParameter("newPassword");//新密码
		InsurUserInfo insurUserInfo = (InsurUserInfo) req.getSession().getAttribute(Constants.SYS_LOGIN_USR);
		try {
			oldPassword = MD5Util.md5Hex(MD5Util.md5Hex(oldPassword));
			newPassword = MD5Util.md5Hex(MD5Util.md5Hex(newPassword));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!oldPassword.equals(insurUserInfo.getLoginPwd())){
			response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("旧密码错误");
			return response;
		}
		insurUserInfo.setLoginPwd(newPassword);
		response = iuserService.changePassword(insurUserInfo);
		LOGGER.info("******修改密码:操作人:"+insurUserInfo.getLoginAccount());
		return response;
	}
}
