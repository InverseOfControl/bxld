package com.ymkj.bxld.dao.attachment.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.bxld.dao.attachment.IAttachmentDao;
import com.ymkj.bxld.domain.attachment.AttchmentVO;

@Repository
public class AttachmentDaoImpl extends BaseDaoImpl<AttchmentVO> implements IAttachmentDao {

	@Override
	public void insertAttachment(AttchmentVO attchmentVO) {
		getSqlSession().insert("insertAttachment", attchmentVO);
	}

	@Override
	public void insertOrderAttachment(AttchmentVO attchmentVO) {
		getSqlSession().insert("insertOrderAttachment", attchmentVO);
	}

	@Override
	public List<Map<String, Object>> getFileInfo(AttchmentVO attchmentVO) {
		return getSqlSession().selectList("getFileInfo", attchmentVO);
	}

	@Override
	public String getFilePathById(String id) {
		return getSqlSession().selectOne("getFilePathById", id);
	}

	@Override
	public long updateFileInfo(AttchmentVO attchmentVO) {
		return getSqlSession().update("updateFileInfo", attchmentVO);
	}

	@Override
	public long updateHistoryFileInfo(Map<String, Object> paramMap) {
		return getSqlSession().update("updateHistoryFileInfo", paramMap);
	}

	@Override
	public List<Map<String, Object>> exportUserInfo(List<String> orderCodeList) {
		return getSqlSession().selectList("exportUserInfo", orderCodeList);
	}

}
