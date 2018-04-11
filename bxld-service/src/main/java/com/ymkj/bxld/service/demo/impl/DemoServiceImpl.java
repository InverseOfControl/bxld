package com.ymkj.bxld.service.demo.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.bxld.dao.demo.IDemoDao;
import com.ymkj.bxld.domain.demo.DemoVO;
import com.ymkj.bxld.service.demo.IDemoService;

@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoVO> implements IDemoService {

	//日志
	private static final Logger logger = Logger.getLogger(DemoServiceImpl.class);
	
	@Autowired
	private IDemoDao demoDao;
	
	@Override
	protected BaseDao<DemoVO> getDao() {
		return demoDao;
	}
	
	@Override
	public PageResponse<DemoVO> testPage(DemoVO demo) {
		PageResponse<DemoVO> response = new PageResponse<DemoVO>();
		
		PageParam pageParam = new PageParam(demo.getPageNum(), demo.getPageSize());
		Map<String,Object> paramMap = null;
		
		//实体对象转成请求map
		try {
			paramMap = BeanKit.bean2map(demo);
		} catch (Exception e) {
		}
		PageBean<DemoVO> pageBean = demoDao.listPage(pageParam,paramMap);
		List<DemoVO> pageList = pageBean.getRecords();
		
		response.setRecords(pageList);
		//复制分页信息到响应对象
		BeanUtils.copyProperties(pageBean, response, "records");
		return response;
	}
	
}
