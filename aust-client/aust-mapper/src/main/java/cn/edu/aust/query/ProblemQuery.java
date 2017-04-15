package cn.edu.aust.query;

/**
 * 题目查询类,封装查询和排序条件
 * 注意防止sql注入
 * @author Niu Li
 * @since  2017/1/29
 */
public class ProblemQuery {
    /**
     * 限制搜索最大15个字符
     */
    private static final Integer searchLimit = 15;
    /**
     * 搜索内容 id 标题 标签
     */
    private String search;
    /**
     * 查询阶段
     */
    private Integer stage;
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
        if ("asc".equals(direction)){
            return "asc";
        }
        return "desc";
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }
}
