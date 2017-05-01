package cn.edu.aust.common.constant.vote;

/**
 * 点赞类型
 * @author Niu Li
 * @since 2017/4/30
 */
public enum  VoteType {

  UNKONW(0, "未知类型"),
  COMMENT(1, "评论点赞"),
  ARTICLE(2, "文章点赞"),
  ;

  public int value;
  public String msg;

  VoteType(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }
}
