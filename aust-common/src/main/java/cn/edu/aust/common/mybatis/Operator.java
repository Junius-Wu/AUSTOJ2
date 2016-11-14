package cn.edu.aust.common.mybatis;

/**
 * 数据库操作符
 * @author Niu Li
 * @date 2016/11/4
 */
public enum Operator {

    /** 等于 */
    eq(" = "),

    /** 不等于 */
    ne(" != "),

    /** 大于 */
    gt(" > "),

    /** 小于 */
    lt(" < "),

    /** 大于等于 */
    ge(" >= "),

    /** 小于等于 */
    le(" <= "),

    /** 包含 */
    in(" in "),

    /** 为Null */
    isNull(" is NULL "),

    /** 不为Null */
    isNotNull(" is not NULL "),

    /** 类似 */
    like(" like "),

    /** 不类似 */
    noLike(" not like ")
    ;

    Operator(String operator) {
        this.operator = operator;
    }

    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
