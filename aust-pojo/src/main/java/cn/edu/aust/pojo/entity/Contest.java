package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    /**
     * 1校赛,2公开赛
     */
    private Byte type;

    /**
     * 所需密码
     */
    private String password;

    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 1有效,0无效
     */
    private Byte defunct;

    private Date createdate;

    private Date modifydate;

    /**
     * 描述信息
     */
    private String description;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取1校赛,2公开赛
     *
     * @return type - 1校赛,2公开赛
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置1校赛,2公开赛
     *
     * @param type 1校赛,2公开赛
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取所需密码
     *
     * @return password - 所需密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置所需密码
     *
     * @param password 所需密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取创建用户
     *
     * @return create_user - 创建用户
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建用户
     *
     * @param createUser 创建用户
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取1有效,0无效
     *
     * @return defunct - 1有效,0无效
     */
    public Byte getDefunct() {
        return defunct;
    }

    /**
     * 设置1有效,0无效
     *
     * @param defunct 1有效,0无效
     */
    public void setDefunct(Byte defunct) {
        this.defunct = defunct;
    }

    /**
     * @return createdate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return modifydate
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * @param modifydate
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * 获取描述信息
     *
     * @return description - 描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述信息
     *
     * @param description 描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }
}