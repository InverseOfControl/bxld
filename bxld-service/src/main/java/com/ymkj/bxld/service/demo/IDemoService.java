package com.ymkj.bxld.service.demo;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.bxld.domain.demo.DemoVO;

/**
 * 业务层
 * @author YM10159
 */
public interface IDemoService extends BaseService<DemoVO> {
	
	/**
	 * description:测试方法
	 * autor:ym10159
	 * date:2017年12月7日 上午10:39:12
	 * @param demo 请求对象
	 * @return 返回分页对象
	 */
	public PageResponse<DemoVO> testPage(DemoVO demo);
}
