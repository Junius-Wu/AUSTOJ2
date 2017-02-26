package cn.edu.aust.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 从request中获取想要的值
 *
 * @author Niu Li
 * @since 2017/2/26
 */
public final class CgiHelper {

  /**
   * 从请求中获取字符串类型值
   *
   * @param key          指定key
   * @param defaultValue 不存在的value
   * @param request      该请求
   * @return 值
   */
  public static String getString(String key, String defaultValue, HttpServletRequest request) {
    String value = request.getParameter(key);
    return value == null ? defaultValue : value;
  }
}
