package cn.edu.aust.common.entity;

import java.util.Date;

public class ReportLog {
    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer otherId;

    private Byte status;

    private Date createdate;

    public ReportLog(Integer id, Integer userId, Byte type, Integer otherId, Byte status, Date createdate) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.otherId = otherId;
        this.status = status;
        this.createdate = createdate;
    }

    public ReportLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getOtherId() {
        return otherId;
    }

    public void setOtherId(Integer otherId) {
        this.otherId = otherId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}