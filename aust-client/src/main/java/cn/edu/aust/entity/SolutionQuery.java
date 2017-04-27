package cn.edu.aust.entity;

/**
 * 提交表查询实体
 * @author Niu Li
 * @since 2017/3/18
 */
public class SolutionQuery {
  /**
   * 限制搜索最大15个字符
   */
  private static final Integer searchLimit = 15;

  private String search;

  private Integer userId;

  /**
   * 排序方向
   */
  private String direction = "desc";

  public String getSearch() {
    if (search != null && search.length()>searchLimit){
      search = search.substring(0,searchLimit);
    }
    return search!=null ? "%"+search+"%" : null;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
