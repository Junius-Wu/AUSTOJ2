package cn.edu.aust.common.constant;

/**
 * 错误码定义
 *
 * @author Niu Li
 * @since 2017/2/19
 */
public enum JudgeCode {
  OK(0, "SUCCESS"), NO_LANGUAGE(99, "不支持的语言类型"), SYS_ERROR(96, "系统异常"),
  COMPILE_ERROR(97, "编译错误"), NO_TESTCASE(98, "不存在测试案例");


  private Integer status;
  private String msg;

  JudgeCode(int status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}