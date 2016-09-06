package cn.edu.aust.entity;

public class Catelog {
    private Integer id;

    private String name;

    private Byte type;

    public Catelog(Integer id, String name, Byte type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Catelog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}