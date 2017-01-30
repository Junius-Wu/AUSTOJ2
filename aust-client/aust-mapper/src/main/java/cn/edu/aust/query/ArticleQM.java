package cn.edu.aust.query;

/**
 * 文章查询包装类
 * @author Niu Li
 * @date 2017/1/30
 */
public class ArticleQM {
    /**
     * 限制搜索最大15个字符
     */
    private static final Integer searchLimit = 15;
    /**
     * 搜索id 标题 标签
     */
    private String search;
    /**
     * 当前用户id
     */
    private Long userId;

    public String getSearch() {
        if (search != null && search.length()>searchLimit){
            search = search.substring(0,searchLimit);
        }
        return search!=null ? "%"+search+"%" : null;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
