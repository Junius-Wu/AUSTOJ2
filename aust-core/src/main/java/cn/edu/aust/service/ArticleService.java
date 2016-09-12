package cn.edu.aust.service;

import cn.edu.aust.entity.Article;
import cn.edu.aust.entity.ArticleBLOBs;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface ArticleService {

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleBLOBs record);

    int insertSelective(ArticleBLOBs record);

    ArticleBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleBLOBs record);

    int updateByPrimaryKey(Article record);
}
