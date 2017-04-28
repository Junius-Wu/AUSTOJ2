package cn.edu.aust.common.constant.user;

/**
 * @author Niu Li
 * @since 2017/4/15
 */
public enum UserType {

  UNKNOW(0,"未知类型"),
  GENERAL(1,"普通用户"),
  ADMIN(2,"管理员用户"),
  ;

  public int value;
  public String msg;

  UserType(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }
}
