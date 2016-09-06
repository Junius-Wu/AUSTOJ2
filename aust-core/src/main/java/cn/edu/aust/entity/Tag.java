package cn.edu.aust.entity;

public class Tag {
    private Integer id;

    private String tag;

    private Integer count;

    public Tag(Integer id, String tag, Integer count) {
        this.id = id;
        this.tag = tag;
        this.count = count;
    }

    public Tag() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}