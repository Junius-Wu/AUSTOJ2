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

  public int value;
  public String msg;

  UserStatus(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }
}
