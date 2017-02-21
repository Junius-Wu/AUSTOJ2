package cn.edu.aust.judger.core;

import java.util.HashMap;
import java.util.Map;

import cn.edu.aust.judger.util.Constant;
import cn.edu.aust.judger.util.LanguageUtil;
import cn.edu.aust.judger.util.NativeLibraryLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * 本地程序执行器, 用于执行本地应用程序. 包括编译器(gcc)以及用户提交的代码所编译出的程序.
 *
 * @author Haozhe Xie
 */
@Slf4j
public class Runner {

  /**
   * Load Native Library.
   */
  static {
    try {
      NativeLibraryLoader.loadLibrary("JudgerCore");
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error("加载judgerCore库失败", ex);
    }
  }

  /**
   * 获取执行数据结果
   * @param submission 对应提交的id
   * @param commandLine 执行命令
   * @param inputFilePath 输入数据路径
   * @param outputFilePath 结果输出路径
   * @param timeLimit 时间限制
   * @param memoryLimit 内存限制e
   * @return 一个包含程序运行结果的Map<String, Object>对象
   */
  public Map<String, Object> getRuntimeResultRun(long submission,String
      commandLine, String
      inputFilePath, String outputFilePath,int timeLimit,int memoryLimit) {
    log.info("Start running with command: {}",commandLine);
    Map<String, Object> result = new HashMap<String, Object>(4, 1);
    String runtimeResultSlug = "SE";
    int usedTime = 0;
    int usedMemory = 0;
    try {
      log.info("submission {} Start running with command {} (TimeLimit={},MemoryLimit={})",
          submission,commandLine, timeLimit, memoryLimit);
      Map<String, Object> runtimeResult = getRuntimeResult(commandLine,
          Constant.systemUsername, Constant.systemPassword, inputFilePath, outputFilePath,
          timeLimit, memoryLimit);
      log.debug("判题结果: {}",runtimeResult);
      int exitCode = (Integer) runtimeResult.get("exitCode");
      usedTime = (Integer) runtimeResult.get("usedTime");
      usedMemory = (Integer) runtimeResult.get("usedMemory");
      runtimeResultSlug = getRuntimeResultSlug(exitCode, timeLimit, usedTime, memoryLimit, usedMemory);
    } catch (Exception ex) {
      log.error("solution ={} 判题出错",submission,ex);
    }

    result.put("runtimeResult", runtimeResultSlug);
    result.put("usedTime", usedTime);
    result.put("usedMemory", usedMemory);
    return result;
  }

  /**
   * 根据语言得到待执行命令
   * @param language 语言
   * @param sourcePath 源码路径
   * @return 执行命令
   */
  public String getRunCommandLine(LanguageUtil.Language language,
                                String sourcePath) {

    StringBuilder runCommand = new StringBuilder(language.getRunCommand()
            .replaceAll("\\{filename\\}", sourcePath));

    if ( language.getLanguageName().equalsIgnoreCase("Java") ) {
      int lastIndexOfSpace = runCommand.lastIndexOf("/");
      runCommand.setCharAt(lastIndexOfSpace, ' ');
    }
    return runCommand.toString();
  }

  /**
   * 根据JNI返回的结果封装评测结果.
   *
   * @param exitCode    - 程序退出状态位
   * @param timeLimit   - 最大时间限制
   * @param timeUsed    - 程序运行所用时间
   * @param memoryLimit - 最大空间限制
   * @param memoryUsed  - 程序运行所用空间(最大值)
   * @return 程序运行结果的唯一英文缩写
   */
  public String getRuntimeResultSlug(int exitCode, int timeLimit, int timeUsed, int memoryLimit, int memoryUsed) {
    if (exitCode == 0) {
      // Output will be compared in next stage
      return "AC";
    }
    if (timeUsed >= timeLimit) {
      return "TLE";
    }
    if (memoryUsed >= memoryLimit) {
      return "MLE";
    }
    return "RE";
  }

  /**
   * 获取程序运行结果.
   *
   * @param commandLine    - 待执行程序的命令行
   * @param systemUsername - 登录操作系统的用户名
   * @param systemPassword - 登录操作系统的密码
   * @param inputFilePath  - 输入文件路径(可为NULL)
   * @param outputFilePath - 输出文件路径(可为NULL)
   * @param timeLimit      - 时间限制(单位ms, 0表示不限制)
   * @param memoryLimit    - 内存限制(单位KB, 0表示不限制)
   * @return 一个包含程序运行结果的Map<String, Object>对象
   */
  public native Map<String, Object> getRuntimeResult(String commandLine,
      String systemUsername, String systemPassword, String inputFilePath,
      String outputFilePath, int timeLimit, int memoryLimit);

}
