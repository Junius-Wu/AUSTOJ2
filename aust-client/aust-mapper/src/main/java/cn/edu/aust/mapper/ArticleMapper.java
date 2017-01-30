package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.Article;
import cn.edu.aust.query.ArticlePC;
import cn.edu.aust.query.ArticleQM;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends Mapper<Article> {
    /**
     * 侧边栏展示专用,含有极少的信息
     * @return 查询列表,分页从外面做
     */
    List<Article> queryForAside();

    /**
     * 查询文章页面显示的列表
     * @return 结果集
     */
    List<ArticlePC> queryList(ArticleQM articleQM);

    ArticlePC queryDetail(Long id);
}