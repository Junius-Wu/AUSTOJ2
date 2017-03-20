package cn.edu.aust.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 文章列表包装类
 *
 * @author Niu Li
 * @date 2017/1/30
 */
@Data
@NoArgsConstructor
@ToString
public class ArticleListDTO {
    private Long id;
    private String title;
    private String keyword;
    private Integer viewcount;
    private String likecount;
    private Byte isTop;
    private Date createdate;
    private Byte isVote;
    private String nickname;
    private String summary;
}
