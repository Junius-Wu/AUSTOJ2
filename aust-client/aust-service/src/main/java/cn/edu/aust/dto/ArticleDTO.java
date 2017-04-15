package cn.edu.aust.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 文章详细
 * @author Niu Li
 * @date 2017/1/30
 */
@Data
@NoArgsConstructor
@ToString
public class ArticleDTO {

    public static final String ARTICLEHIT_COOKIES = "view_articles";

    private Long id;

    private String title;

    private String keyword;

    private Integer viewCount;

    private Byte isTop;

    private Date createDate;

    private Integer likeCount;

    private String nickName;

    private String htmlContent;

    private Integer isShow;
}
