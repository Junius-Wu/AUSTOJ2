package cn.edu.aust.common.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 构建查询参数所需要的类
 * @author Niu Li
 * @date 2016/11/4
 */
public class QueryParams implements Serializable {
    /**
     * 使用and连接的条件集合
     */
    private List<Filter> andFilters = new ArrayList<>();
    /**
     * 使用or连接的条件集合
     */
    private List<Filter> orFilters = new ArrayList<>();
    /**
     * 排序字段
     */
    private List<Order> orders = new ArrayList<>();

    /**
     * 构造该查询实体
     * @return 该实体
     */
    public static QueryParams of(){
        return new QueryParams();
    }

    /**
     * 增加and查询参数
     * @param filter 参数
     * @return 该查询实体
     */
    public QueryParams and(Filter filter){
        andFilters.add(filter);
        return this;
    }
    /**
     * 增加and查询参数,多个
     * @param filter 参数
     * @return 该查询实体
     */
    public QueryParams and(Filter ... filter){
        andFilters.addAll(Arrays.asList(filter));
        return this;
    }
    /**
     * 增加or查询参数
     * @param filter 参数
     * @return 该查询实体
     */
    public QueryParams or(Filter filter){
        orFilters.add(filter);
        return this;
    }
    /**
     * 增加or查询参数,多个
     * @param filter 参数
     * @return 该查询实体
     */
    public QueryParams or(Filter ... filter){
        orFilters.addAll(Arrays.asList(filter));
        return this;
    }
    /**
     * 增加order排序参数
     * @param order 参数
     * @return 该查询实体
     */
    public QueryParams order(Order order){
        orders.add(order);
        return this;
    }
    /**
     * 增加order排序参数
     * @param order 参数
     * @return 该查询实体
     */
    public QueryParams order(Order ... order){
        orders.addAll(Arrays.asList(order));
        return this;
    }

    public List<Filter> getAndFilters() {
        return andFilters;
    }

    public void setAndFilters(List<Filter> andFilters) {
        this.andFilters = andFilters;
    }

    public List<Filter> getOrFilters() {
        return orFilters;
    }

    public void setOrFilters(List<Filter> orFilters) {
        this.orFilters = orFilters;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
