package cn.edu.aust.common.constant;

/**
 * @author Niu Li
 * @since 2017/4/26
 */
public enum ProblemType {

  UNKNOW(0, "未知类型"),
  NORMAL(1, "正常类型"),
  CONTEST(2,"竞赛类型")
  ;

  public int value;
  public String msg;

  ProblemType(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }
}
