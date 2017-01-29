package cn.edu.aust.query;

import cn.edu.aust.pojo.entity.Problem;

/**
 * 题目的包装类
 * @author Niu Li
 * @date 2017/1/29
 */
public class ProblemPC extends Problem{
    /**
     * 上传作者名,来源user表
     */
    private String auchor;
    /**
     * 所属目录
     */
    private String categlogName;

    public String getAuchor() {
        return auchor;
    }

    public void setAuchor(String auchor) {
        this.auchor = auchor;
    }

    public String getCateglogName() {
        return categlogName;
    }

    public void setCateglogName(String categlogName) {
        this.categlogName = categlogName;
    }
}
