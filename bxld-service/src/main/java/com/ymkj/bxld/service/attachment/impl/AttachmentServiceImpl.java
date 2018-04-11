package com.ymkj.bxld.service.attachment.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.bxld.common.exception.BizErrorCode;
import com.ymkj.bxld.common.file.FileZipUtil;
import com.ymkj.bxld.common.file.ZipKit;
import com.ymkj.bxld.common.util.DateUtil;
import com.ymkj.bxld.config.SystemProperties;
import com.ymkj.bxld.dao.attachment.IAttachmentDao;
import com.ymkj.bxld.domain.attachment.AttchmentVO;
import com.ymkj.bxld.service.attachment.IAttachmentService;

@Service
public class AttachmentServiceImpl extends BaseServiceImpl<AttchmentVO> implements IAttachmentService {

	//日志
	private static final Logger logger = Logger.getLogger(AttachmentServiceImpl.class);
	@Autowired
	private IAttachmentDao attachmentDao;
	@Autowired
	private SystemProperties systemProperties;
	
	@Override
	protected BaseDao<AttchmentVO> getDao() {
		return attachmentDao;
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public Response<Object> upload(AttchmentVO attchmentVO) {
		Response<Object> resp = new Response<Object>();
		List<String> fileInfoList = new ArrayList<>();
		
		List<MultipartFile>  fileList = attchmentVO.getUploadFileList();
		if(CollectionUtils.isEmpty(fileList)){
			resp.setRepCode(BizErrorCode.EOERR.getErrorCode());
			resp.setRepMsg("请上传文件！");
			return resp;
		}
		
		//创建包名
		String dateFolder = DateUtil.format(new Date(), DateUtil.pattern_day);
		String orderFolder = attchmentVO.getOrderCode();
		String fileFolder = systemProperties.getFileUploadPath()+dateFolder+"/"+orderFolder;
		File folder = new File(fileFolder);
		if(!folder.exists() && !folder.isDirectory()){
			folder.mkdirs();
		}
		
		//解析文件并上传
		for (MultipartFile multipartFile : fileList) {
			String orginFileName = multipartFile.getOriginalFilename();
			String fileExt = orginFileName.substring(orginFileName.lastIndexOf(".")+1, orginFileName.length());
			String newFileName =  attchmentVO.getBussinessType()+"_"+this.getUUID()+"."+fileExt;
					
			attchmentVO.setOldAttachmentName(orginFileName);
			attchmentVO.setAttachmentType(fileExt);
			attchmentVO.setAttachmentUrl(fileFolder+"/"+newFileName);
			attchmentVO.setNewAttachmentName(newFileName);
			
			if(this.copyFile(multipartFile,newFileName,fileFolder)){
				//插入附件信息表
				attachmentDao.insertAttachment(attchmentVO);
				//插入订单附件信息表
				attachmentDao.insertOrderAttachment(attchmentVO);
				
				fileInfoList.add(fileFolder+"/"+newFileName);
			}
		}
		resp.setData(fileInfoList);
		return resp;
	}
	
	/**
	 * description:保存附件到指定文件目录
	 * autor:ym10159
	 * date:2017年12月13日 下午3:35:53
	 * @param file 要保存的文件
	 * @param newFileName 新的文件名
	 * @param fileFolder 文件上传目录
	 * @return 是否保存成功
	 */
	private boolean copyFile(MultipartFile file, String newFileName, String fileFolder){
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileFolder, newFileName));
		} catch (Exception e) {
			logger.error("文件上传失败,异常信息：", e);
			return false;
		}
		return true;
	}
	
	public String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public Response<Object> getFileInfo(AttchmentVO attchmentVO) {
		Response<Object> response = new Response<Object>();
		List<Map<String,Object>> fileList = attachmentDao.getFileInfo(attchmentVO);
		response.setData(fileList);
		return response;
	}

	@Override
	public String getFilePathById(String id) {
		return attachmentDao.getFilePathById(id);
	}

	@Override
	public long updateFileInfo(AttchmentVO attchmentVO) {
		logger.info("更新订单附件关系表,param:"+ JSON.toJSONString(attchmentVO));
		long updateCode = -1;
		try {
			updateCode = attachmentDao.updateFileInfo(attchmentVO);
		} catch (Exception e) {
			logger.error("更新订单附件关系表异常，exception：",e);
			e.printStackTrace();
		}
		return updateCode;
	}

	@Override
	public long updateHistoryFileInfo(Map<String, Object> paramMap) {
		return attachmentDao.updateHistoryFileInfo(paramMap);
	}

	@Override
	public String download(List<String> orderCodeList) {
		//查询出需要到处的用户信息,根据用户信息创建导出目录
		List<Map<String,Object>> userInfoList = attachmentDao.exportUserInfo(orderCodeList);
		String zipDownloadPath = ""; //压缩包下载路径 
		
		for (int i = 0; i < userInfoList.size(); i++) {
			String orderCode = ObjectUtils.toString(userInfoList.get(i).get("order_code"));
			String companyName = ObjectUtils.toString(userInfoList.get(i).get("company_name"));
			String plateNumber = ObjectUtils.toString(userInfoList.get(i).get("plate_number"));
			String consumerName = ObjectUtils.toString(userInfoList.get(i).get("consumer_name"));
			//用户资料存放目录
			String userFileDirPath = companyName+"_"+plateNumber+"_"+consumerName;
			//压缩用户资料存放目录
			zipDownloadPath = copyFileAndZip(userFileDirPath,orderCode);
		}
		return zipDownloadPath;
	}
	
	/**
	 * description:复制文件到临时目录并压缩此目录
	 * autor:ym10159
	 * date:2017年12月22日 下午2:23:38
	 * @param userFileDirPath 保存用户资料的目录
	 */
	private String copyFileAndZip(String userFileDirPath, String orderCode){
		String baseDir = "F:\\tmpFile";
		String rootDirPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String destDirPath = baseDir + File.separator + rootDirPath + File.separator + userFileDirPath;
		File destDirFile = new File(destDirPath);
		//用户资料创建目录
		if(!destDirFile.exists()){
			destDirFile.mkdirs();
		}
		//查询要下载的用户附件信息
		AttchmentVO attchmentVO = new AttchmentVO();
		attchmentVO.setBussinessType("SQ");
		attchmentVO.setStatus("0");
		attchmentVO.setOrderCode(orderCode);
		List<Map<String,Object>> fileList = attachmentDao.getFileInfo(attchmentVO);
		//把要下载的文件拷贝到用户资料目录中
		for (int i = 0; i < fileList.size(); i++) {
			File srcFile = new File(fileList.get(i).get("attachment_url").toString());
			try {
				FileUtils.copyFileToDirectory(srcFile, destDirFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//用户信息写入到文件
		try {
			writeUserInfoToFile(orderCode, destDirPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//压缩临时目录
		String srcDirPath = baseDir+File.separator+rootDirPath; //压缩目录
		String zipDirPath = baseDir; //压缩文件存放目录
		String zipFileName = rootDirPath+".zip"; //压缩文件名
		FileZipUtil.compressedFile(srcDirPath, zipDirPath, zipFileName);
		
		return baseDir+File.separator+zipFileName;
	}
	
	/**
	 * description:读取订单信息并写入文件
	 * autor:ym10159
	 * date:2017年12月25日 上午9:35:37
	 * @param orderCodeList
	 */
	private void writeUserInfoToFile(String orderCode,String destDirPath) throws IOException{
		File file = new File(destDirPath+File.separator+"info.txt");
		//下面的内容换成需要导出的内容
		String testContent = "士大夫极乐世界的法律涉及到法律士大夫";
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		os.write(testContent.getBytes());
		os.close();
	}
}
