package com.ymkj.bxld.controller.attachment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.attachment.AttchmentVO;
import com.ymkj.bxld.service.attachment.IAttachmentService;

/**
 * 附件上传控制层
 * @author YM10159
 */
@Controller
@RequestMapping("/file")
public class AttachmentController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(AttachmentController.class);
	@Autowired
	private IAttachmentService attachmentService;
	
	/**
	 * description:附件上传
	 * autor:ym10159
	 * date:2017年12月14日 上午11:36:32
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param attchmentVO 附件对象
	 * @return 上传结果
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)  
	@ResponseBody
	public Response<Object> uploadFile(HttpServletRequest request,HttpServletResponse response, AttchmentVO attchmentVO ) {
		//下面这段代码必须由，否则前端收不到返回的结果
		response.setHeader("Access-Control-Allow-Origin", "*");
		return attachmentService.upload(attchmentVO);
	}
	
	/**
	 * description:获取图片
	 * autor:ym10159
	 * date:2017年12月14日 上午11:37:14
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	@RequestMapping(value = "/getImg", method = RequestMethod.GET)
	public void getImg(HttpServletRequest request, HttpServletResponse response, AttchmentVO attchmentVO) {
		
		File file = new File(attachmentService.getFilePathById(ObjectUtils.toString(attchmentVO.getId())));
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			response.reset();
			os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
			os.close();
		} catch (Exception e) {
			logger.error("附件获取失败",e);
		}
	}
	
	/**
	 * description:附件下载
	 * autor:ym10159
	 * date:2017年12月21日 下午4:19:24
	 * @throws IOException 
	 */
	@RequestMapping(value = "/download",method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<String> orderCodeList = new ArrayList<>();
		orderCodeList.add("2017006");
		String zipDownloadPath = attachmentService.download(orderCodeList);
		
		File file = new File(zipDownloadPath);
		InputStream fis = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		response.reset();
		response.setCharacterEncoding("UTF-8");
		String filename = file.getName().replaceAll("\\s", "");
		filename = URLEncoder.encode(filename,"UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
	}
}
