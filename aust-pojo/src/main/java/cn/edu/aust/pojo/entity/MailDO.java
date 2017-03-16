package cn.edu.aust.pojo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "mail")
@Data
@NoArgsConstructor
@ToString
public class MailDO {
    /**
     * 站内信主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    private Integer friend;

    private Integer sender;

    private Integer receiver;

    private Byte type;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "read_time")
    private Date readTime;

    private Byte status;

    private String content;

}