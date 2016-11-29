package cn.edu.aust.service;


import java.util.List;

import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.ArticleBLOBs;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.entity.pojo.ArticleUser;

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

    List<Article> selectList(Integer count);

    /**
     * 查询展示列表
     * @param search 搜索字段
     * @return 查询集合
     */
    List<ArticleUser> selectAll(User user,String search);

    /**
     * 查找单条文章记录
     * @param articleId 文章id
     * @return 查询实体
     */
    ArticleUser selectByPk(User user,Integer articleId);
}
