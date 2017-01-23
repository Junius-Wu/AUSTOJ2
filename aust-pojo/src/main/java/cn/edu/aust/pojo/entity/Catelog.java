package cn.edu.aust.pojo.entity;

import javax.persistence.*;

@Table(name = "catelog")
public class Catelog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * 0题目分类
     */
    private Byte type;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取0题目分类
     *
     * @return type - 0题目分类
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置0题目分类
     *
     * @param type 0题目分类
     */
    public void setType(Byte type) {
        this.type = type;
    }
}