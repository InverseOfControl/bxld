package com.ymkj.bxld.common.util;

import java.util.Date;
import java.util.Random;

/**
 * 生成单号
 * @author YM10159
 */
public class GenerateOrderNoUtil {
	
	public static String createAppNo(Date date){
		String dateStr = DateUtil.defaultFormatYYYYMMDD(date);
		return dateStr + randomHexString(6);
	}
	
	public static String random() {
		int count = 6;
        char start = '0';
        char end = '9';
 
        Random rnd = new Random();
 
        char[] result = new char[count];
        int len = end - start + 1;
 
        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }
        return new String(result);
	}
	
	public static int getRandomNumber(int min,int max){
		if(min == max){
			return 0;
		}
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	
    public static String randomHexString(int len)  {  
        try {  
            StringBuffer result = new StringBuffer();  
            for(int i=0;i<len;i++) {  
                result.append(Integer.toHexString(new Random().nextInt(16)));  
            }  
            return result.toString().toUpperCase();  
        } catch (Exception e) {  
            return null;
        }  
          
    }
}
