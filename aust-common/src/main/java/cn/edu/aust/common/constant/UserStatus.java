package cn.edu.aust.common.constant;

/**
 * @author Niu Li
 * @since 2017/4/15
 */
public enum UserStatus{

  NORMAL(1,"正常"),
  WAIT4EMAIL_CHECK(2,"待邮箱验证"),
  FREEZE(3,"冻结")
  ;

  public int code;
  public String msg;

  UserStatus(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
