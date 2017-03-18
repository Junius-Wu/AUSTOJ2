package cn.edu.aust.common.constant;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * 错误码定义
 *
 * @author Niu Li
 * @since 2017/2/19
 */
public enum JudgeCode {
  AC(0, "SUCCESS"),COMPILEING(1,"正在编译"), NO_LANGUAGE(99, "不支持的语言类型"), SYS_ERROR(96, "系统异常"),
  COMPILE_ERROR(97, "编译错误"), NO_TESTCASE(98, "不存在测试案例");


  private static final Map<Integer,JudgeCode> existCode = Maps.newHashMap();

  static {
    existCode.put(AC.getStatus(),AC);
  }

  private Integer status;
  private String msg;

  JudgeCode(int status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public Integer getStatus() {
    return status;
  }

  public String getMsg() {
    return msg;
  }

  /**
   * 根据错误码快速定位到对应枚举类
   * @param status 错误码
   * @return 枚举类
   */
  public static JudgeCode statusOf(int status){
    JudgeCode judgeCode = existCode.get(status);
    return Objects.isNull(judgeCode)?SYS_ERROR:judgeCode;
  }

}