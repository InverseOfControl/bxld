package com.ymkj.bxld.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 枚举工具类 Created by wulinjie
 */
public class EnumerationUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(EnumerationUtils.class);

  /**
   * 根据枚举类的code获取value
   *
   * @param clzz 枚举类
   * @param code 枚举Code
   * @return
   */
  public static String getValueByCode(Class clzz, String code) {
    try {
      Object[] consts = clzz.getEnumConstants();
      for (Object item : consts) {
        String _code = BeanUtils.getProperty(item, "code");
        if(_code.equals(code)) {//修复了code为null报错问题
          return BeanUtils.getProperty(item, "value");
        }
      }
    } catch (Exception e) {
      LOGGER.error("根据Code获取枚举Value异常", e);
    }

    return "";
  }
}
