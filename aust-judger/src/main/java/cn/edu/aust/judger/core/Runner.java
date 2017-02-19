package cn.edu.aust.judger.core;

import java.util.HashMap;
import java.util.Map;

import cn.edu.aust.judger.util.Constant;
import cn.edu.aust.judger.util.NativeLibraryLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * 本地程序执行器, 用于执行本地应用程序. 包括编译器(gcc)以及用户提交的代码所编译出的程序.
 *
 * @author Haozhe Xie
 */
@Slf4j
public class Runner {

  public static void main(String[] args) {
    Runner runner = new Runner();
    String workBaseDirectory = "/tmp";

    String inputFilePath = workBaseDirectory + "/testpoints/1001/data1.in";
    String outputFilePath = workBaseDirectory + "/voj-1001/output#0.txt";

    //编译
    runner.getRuntimeResult("javac /tmp/voj-1001/Main.java", "root", "7946521",
        null, "/tmp/voj-1001/compile.log", 2000, 32768);
    //执行
    Map<String, Object> result = runner.getRuntimeResult("java -cp /tmp/voj-1001/ " +
            "Main", "root", "7946521",
        inputFilePath, outputFilePath, 2000, 32768);
    System.out.println(result);
  }

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
   * @param codeSource 源码
   * @param commandLine 执行命令
   * @param inputFilePath 输入数据路径
   * @param outputFilePath 结果输出路径
   * @param timeLimit 时间限制
   * @param memoryLimit 内存限制
   * @return 一个包含程序运行结果的Map<String, Object>对象
   */
  public Map<String, Object> getRuntimeResultRun(long submission,String codeSource,String
      commandLine, String
      inputFilePath, String outputFilePath,int timeLimit,int memoryLimit) {

    Map<String, Object> result = new HashMap<String, Object>(4, 1);
    String runtimeResultSlug = "SE";
    int usedTime = 0;
    int usedMemory = 0;
    try {
      log.info("submission %d Start running with command %s (TimeLimit=%s,MemoryLimit=%s)",
          submission,commandLine, timeLimit, memoryLimit);
      Map<String, Object> runtimeResult = getRuntimeResult(commandLine,
          Constant.systemUsername, Constant.systemPassword, inputFilePath, outputFilePath,
          timeLimit, memoryLimit);

      int exitCode = (Integer) runtimeResult.get("exitCode");
      usedTime = (Integer) runtimeResult.get("usedTime");
      usedMemory = (Integer) runtimeResult.get("usedMemory");
      runtimeResultSlug = getRuntimeResultSlug(exitCode, timeLimit, usedTime, memoryLimit, usedMemory);
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error("判题出错",ex);
    }

    result.put("runtimeResult", runtimeResultSlug);
    result.put("usedTime", usedTime);
    result.put("usedMemory", usedMemory);
    return result;
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
  private String getRuntimeResultSlug(int exitCode, int timeLimit, int timeUsed, int memoryLimit, int memoryUsed) {
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
   * 获取(编译)程序运行结果.
   *
   * @param commandLine    - 待执行程序的命令行
   * @param inputFilePath  - 输入文件路径(可为NULL)
   * @param outputFilePath - 输出文件路径(可为NULL)
   * @param timeLimit      - 时间限制(单位ms, 0表示不限制)
   * @param memoryLimit    - 内存限制(单位KB, 0表示不限制)
   * @return 一个包含程序运行结果的Map<String, Object>对象
   */
  public Map<String, Object> getRuntimeResultCompile(String commandLine, String inputFilePath,
      String outputFilePath,
      int timeLimit, int memoryLimit) {
    Map<String, Object> result = null;
    try {
      result = getRuntimeResult(commandLine, Constant.systemUsername, Constant.systemPassword,
          inputFilePath, outputFilePath, timeLimit, memoryLimit);
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error("jni调用失败",ex);
    }
    return result;
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
