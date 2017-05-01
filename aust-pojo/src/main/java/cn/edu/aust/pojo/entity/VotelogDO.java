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

@Table(name = "votelog")
@Data
@NoArgsConstructor
@ToString
public class VotelogDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     */
    private Integer type;

    /**
     * 点赞对象的id
     */
    @Column(name = "other_id")
    private Long otherId;

    /**
     * 0取消,1有效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

}