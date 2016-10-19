package cn.edu.aust.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
public class PageAble implements Serializable {

    private static final long serialVersionUID = -3930180379790344299L;

    private Integer limit = 15;

    private Integer offset = 1;

    private String order = "desc";

    private String ordername = "id";

    /** 搜索属性 */
    private String search;

    /** 筛选 */
    private List<Filter> filters = new ArrayList<Filter>();


    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

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
        if (search != null) {
            this.search = "%"+search+"%";
        }else {
            this.search = search;
        }
    }
}
