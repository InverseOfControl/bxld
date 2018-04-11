package com.ymkj.bxld.common.file;
  
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;  
  
public class FileZipUtil {  
  
    /** 
     * @description:将文件/文件夹生成指定格式的压缩文件
     * @autor:ym10159
	 * @date:2017年12月22日 上午9:53:13
     * @param srcPath 源文件路径
     * @param targetPath 压缩文件保存路径 
     */  
    public static void compressedFile(String srcPath, String targetPath, String zipFileName){  
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath); 
        //压缩目录不存在，则创建
        if (targetFile.isDirectory() && !targetFile.exists()) {  
        	targetFile.mkdirs();  
        }  
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(targetPath + File.separator + zipFileName);
			zos = new ZipOutputStream(new BufferedOutputStream(fos));  
			compress(zos, srcFile, ""); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != zos){
					zos.close();
				}
				if(null != fos){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
    }  
  
    private static void compress(ZipOutputStream zos, File file, String baseDir) throws Exception {  
        if (file.isDirectory()) {  
        	baseDir = file.getName() + File.separator;
        	compressDir(zos, file, baseDir);
        } else if (file.isFile()){ 
        	compressFile(zos, file, baseDir);
        }  
    }  
    
    private static void compressDir(ZipOutputStream zos, File file, String baseDir) throws Exception{
    	 File[] files = file.listFiles();  
         for (int i = 0; i < files.length; i++) {  
        	 /**递归调用*/
             compress(zos, files[i], baseDir);
         }  
    }
    
    private static void compressFile(ZipOutputStream zos, File file, String baseDir) throws Exception{
    	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
    	zos.putNextEntry(new ZipEntry(baseDir + file.getName()));  
    	int len = 0;  
    	byte[] buffer = new byte[1024];  
    	while ((len = bis.read(buffer)) != -1) {  
    		zos.write(buffer, 0, len);  
    	}  
    	bis.close();  
    }
}  