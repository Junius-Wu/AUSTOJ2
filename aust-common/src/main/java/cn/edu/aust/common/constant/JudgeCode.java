package cn.edu.aust.common.constant;

/**
 * 判题错误码
 * @author Niu Li
 * @since 2017/3/15
 */
public enum  JudgeCode {
  SUCCESS("00","SUCCESS"),EXCEPTION("99","判题异常");

  private String code;
  private String msg;

  JudgeCode(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
