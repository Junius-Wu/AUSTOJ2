package cn.edu.aust.entity;

import java.io.Serializable;

/**
 * bootstrap table参数请求封装
 * @author Niu Li
 * @date 2017/1/29
 */
public class PageRequest implements Serializable{
    private static final long serialVersionUID = -3930180379790344299L;

    private Integer limit = 15;

    private Integer offset = 1;

    private String order = "desc";

    private String ordername = "id";

    /** 搜索属性 */
    private String search;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
