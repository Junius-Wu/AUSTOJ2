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

@Table(name = "reportlog")
@Data
@NoArgsConstructor
@ToString
public class ReportlogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 举报用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 举报类型,1题目评论,2文章,3文章评论
     */
    private Byte type;

    /**
     * 举报内容的id
     */
    @Column(name = "other_id")
    private Integer otherId;

    /**
     * 1举报,0撤销
     */
    private Byte status;

    /**
     * 时间
     */
    private Date createdate;
}