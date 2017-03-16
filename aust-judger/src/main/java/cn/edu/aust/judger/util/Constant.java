package cn.edu.aust.judger.util;

import cn.edu.aust.common.util.PropertiesUtils;

/**
 * 存储读取的配置文件,提供刷新service方法,便于动态刷新
 *
 * @author Niu Li
 * @since 17-2-19
 */
public final class Constant {

  private static String CONFIG = "config.properties";
  /**
   * 登录操作系统的用户名.
   * 为了安全, 我们建议评测程序以低权限的用户运行.
   */
  public static String systemUsername;
  /**
   * 登录操作系统的密码.
   * 为了安全, 我们建议评测程序以低权限的用户运行.
   */
  public static String systemPassword;
  /**
   * OJ工作根目录
   */
  public static String baseWorkDirectory;
  /**
   * 测试数据所在目录
   */
  public static String testCaseDirectory;
  /**
   * 判题服务器端口
   */
  public static Integer judgePort;

  static {
    init();
  }

  private static void init() {
    systemUsername = PropertiesUtils.getClassPathProperty("system.username", CONFIG);
    systemPassword = PropertiesUtils.getClassPathProperty("system.password", CONFIG);
    baseWorkDirectory = PropertiesUtils.getClassPathProperty("judger.workDir", CONFIG);
    testCaseDirectory = PropertiesUtils.getClassPathProperty("judge.testCase", CONFIG);
    judgePort = Integer.parseInt(PropertiesUtils.getClassPathProperty("judge.port", CONFIG));
  }

}
