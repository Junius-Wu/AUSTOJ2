package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Table(name = "contest")
@Data
@ToString
public class ContestDO {
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
     * 创建用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 创建用户
     */
    private String username;

    private Date createdate;

    private Date modifydate;

    /**
     * 描述信息
     */
    private String description;

    private Integer status;

    private String extend;

}