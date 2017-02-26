package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Table(name = "contest")
@Data
@ToString
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

}