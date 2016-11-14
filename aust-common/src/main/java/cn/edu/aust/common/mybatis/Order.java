package cn.edu.aust.common.mybatis;

/**
 * 数据库排序属性
 * @author Niu Li
 * @date 2016/11/4
 */
public final class Order {
    /**
     * 要排序的字段
     */
    private String orderName;
    /**
     * 排序方向
     */
    private String direction;

    public Order(){};

    public Order(String orderName, String direction) {
        this.orderName = orderName;
        this.direction = direction;
    }

    /**
     * 降序排列
     * @param orderName 要排序的字段名
     * @return 构造的order
     */
    public static Order descOrder(String orderName){
        return new Order(orderName,"desc");
    }
    /**
     * 升序排列
     * @param orderName 要排序的字段名
     * @return 构造的order
     */
    public static Order ascOrder(String orderName){
        return new Order(orderName,"asc");
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
