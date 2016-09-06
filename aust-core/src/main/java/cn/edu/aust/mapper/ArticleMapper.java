package cn.edu.aust.mapper;

import cn.edu.aust.entity.Article;
import cn.edu.aust.entity.ArticleBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleBLOBs record);

    int insertSelective(ArticleBLOBs record);

    ArticleBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleBLOBs record);

    int updateByPrimaryKey(Article record);
}