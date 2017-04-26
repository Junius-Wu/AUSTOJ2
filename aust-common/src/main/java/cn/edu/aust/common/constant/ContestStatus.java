package cn.edu.aust.common.constant;

/**
 * @author Niu Li
 * @since 2017/4/26
 */
public enum  ContestStatus {

  UNKONW(0, "未知类型"),
  NORMAL(1,"正常"),
  ;

  public int value;
  public String msg;

  ContestStatus(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }
}
