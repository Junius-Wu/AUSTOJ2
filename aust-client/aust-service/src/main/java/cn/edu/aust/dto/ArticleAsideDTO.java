package cn.edu.aust.dto;

/**
 * 文章侧边栏专用DTO
 * @author Niu Li
 * @date 2017/1/29
 */
public class ArticleAsideDTO {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
