package com.ymkj.bxld.domain.attachment;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 附件上传实体
 * @author YM10159
 */
public class AttchmentVO extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 附件表主键
	 */
	private int id;
	/**
	 * 附件扩展名
	 */
	private String attachmentType;
	/**
	 * 附件原名称
	 */
	private String oldAttachmentName;
	/**
	 * 附件存放地址
	 */
	private String attachmentUrl;
	/**
	 * 附件重命名
	 */
	private String newAttachmentName;
	
	/**
	 * 附件所属业务
	 */
	private String bussinessType;
	/**
	 * 订单编号
	 */
	private String orderCode;

	/**
	 * 状态（0-有效， 1-禁用, 2-删除）
	 */
	private String status;
	
	private List<MultipartFile> uploadFileList;

	public List<MultipartFile> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<MultipartFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getOldAttachmentName() {
		return oldAttachmentName;
	}

	public void setOldAttachmentName(String oldAttachmentName) {
		this.oldAttachmentName = oldAttachmentName;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getNewAttachmentName() {
		return newAttachmentName;
	}

	public void setNewAttachmentName(String newAttachmentName) {
		this.newAttachmentName = newAttachmentName;
	}

	public String getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
