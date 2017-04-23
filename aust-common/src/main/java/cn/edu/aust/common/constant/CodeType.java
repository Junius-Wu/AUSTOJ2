package cn.edu.aust.common.constant;

/**
 * 验证码类型
 * @author Niu Li
 * @since 2017/4/22
 */
public enum CodeType {
  REGISTER(1,"注册"),
  FIND_PWD(2,"找回密码")
  ;

  private int value;
  private String msg;

  CodeType(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }

  public static CodeType of(int type){
    for (CodeType codeType : CodeType.values()) {
      if (codeType.getValue() == type) {
        return codeType;
      }
    }
    return null;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
