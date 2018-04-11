package com.ymkj.bxld.controller.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.demo.DemoVO;
import com.ymkj.bxld.service.common.ICommonService;
import com.ymkj.bxld.service.demo.IDemoService;

/**
 * 视图控制层
 * @author YM10159
 */
@Controller
public class DemoController extends BaseController {
	
	@Autowired
	private ICommonService commonService;
	
	@Autowired
	private IDemoService demoService;
	
	@RequestMapping(value = "demo/listPage")
	@ResponseBody
	public PageResponse<DemoVO> listPage(DemoVO demo) {
		demo.setPageNum(2);
		demo.setPageSize(5);
		
		return demoService.testPage(demo);
	}
	
	@RequestMapping(value = "demo/getListData")
	@ResponseBody
	public Object getListData(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("id", "1");
		map.put("code", "0001");
		map.put("name", "测试");
		map.put("isDisabled", "0");
		map.put("operation", "");
		list.add(map);
		return list;
	}
}
