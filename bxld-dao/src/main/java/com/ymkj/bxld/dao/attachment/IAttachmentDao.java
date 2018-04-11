package com.ymkj.bxld.dao.attachment;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.bxld.domain.attachment.AttchmentVO;

public interface IAttachmentDao extends BaseDao<AttchmentVO> {
	
	/**
	 * description:保存附件信息
	 * autor:ym10159
	 * date:2017年12月13日 下午3:31:30
	 */
	public void insertAttachment(AttchmentVO attchmentVO);
	
	/**
	 * description:保存订单附件信息
	 * autor:ym10159
	 * date:2017年12月13日 下午3:31:58
	 */
	public void insertOrderAttachment(AttchmentVO attchmentVO);
	
	/**
	 * description:获取文件信息
	 * autor:ym10159
	 * date:2017年12月14日 下午2:35:33
	 * @return 返回文件信息
	 */
	public List<Map<String,Object>> getFileInfo(AttchmentVO attchmentVO);
	
	/**
	 * description:通过附件ID获取附件地址
	 * autor:ym10159
	 * date:2017年12月18日 下午5:06:40
	 * @param attchmentVO
	 * @return 附件地址
	 */
	public String getFilePathById(String id);

	/**
	 * @discripeion 更新附件信息
	 * @Author lihuimeng
	 * @date 2017/12/19 16:42
	 * @param attchmentVO
	 *
	 */
	long updateFileInfo(AttchmentVO attchmentVO);

	/**
	 * @discripeion 订单处理更新历史附件信息
	 * @Author lihuimeng
	 * @date 2017/12/19 16:40
	 * @param paramMap
	 *
	 */
	public long updateHistoryFileInfo(Map<String,Object> paramMap);
	
	/**
	 * description:导出的用户信息
	 * autor:ym10159
	 * date:2017年12月22日 下午2:01:05
	 * @param orderCode 申请单号
	 * @return 用户列表信息
	 */
	public List<Map<String,Object>> exportUserInfo(List<String> orderCodeList);
}
