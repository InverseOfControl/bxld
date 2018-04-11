package com.ymkj.bxld.service.attachment;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.bxld.domain.attachment.AttchmentVO;

import java.util.List;
import java.util.Map;

public interface IAttachmentService extends BaseService<AttchmentVO> {
	/*
	 * description:附件上传
	 * autor:ym10159
	 * date:2017年12月13日 下午1:50:27
	 * @param attchmentVO 附件信息
	 * @return 响应返回
	 */
	public Response<Object> upload(AttchmentVO attchmentVO);
	
	/**
	 * description:获取文件信息
	 * autor:ym10159
	 * date:2017年12月14日 下午2:35:33
	 * @param attchmentVO 附件信息
	 * @return 返回文件信息
	 */
	public Response<Object> getFileInfo(AttchmentVO attchmentVO);
	
	/**
	 * description:通过附件ID获取附件地址
	 * autor:ym10159
	 * date:2017年12月18日 下午5:06:40
	 * @param id
	 * @return 附件地址
	 */
	public String getFilePathById(String id);


	/**
	 * @discripeion 更新附件信息
	 * @Author lihuimeng
	 * @date 2017/12/19 16:40
	 * @param attchmentVO
	 *
	 */
	public long updateFileInfo(AttchmentVO attchmentVO);

	/**
	 * @discripeion 订单处理更新历史附件信息
	 * @Author lihuimeng
	 * @date 2017/12/19 16:40
	 * @param paramMap
	 *
	 */
	public long updateHistoryFileInfo(Map<String,Object> paramMap);
	
	/**
	 * description:下载用户资料
	 * autor:ym10159
	 * date:2017年12月21日 下午4:30:58
	 * @return 下载的目录路径
	 */
	public String download(List<String> orderCodeList);
}
