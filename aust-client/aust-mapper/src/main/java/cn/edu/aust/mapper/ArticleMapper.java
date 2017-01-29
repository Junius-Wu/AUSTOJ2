package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.Article;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends Mapper<Article> {
    /**
     * 侧边栏展示专用,含有极少的信息
     * @return 查询列表,分页从外面做
     */
    List<Article> queryForAside();
}