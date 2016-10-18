package cn.edu.aust.common.mapper;


import java.util.List;

import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.ArticleBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleBLOBs record);

    int insertSelective(ArticleBLOBs record);

    ArticleBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleBLOBs record);

    int updateByPrimaryKey(Article record);

    List<Article> selectList(Integer count);
}