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

@Table(name = "notify")
@Data
@NoArgsConstructor
@ToString
public class NotifyDO {
    /**
     * 网站通知主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 所关联文章
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 过期时间
     */
    private Date expiredate;

    /**
     * 状态1正常,0待发布,2已过期
     */
    private Byte status;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 修改日期
     */
    private Date modifydate;

}