package cn.edu.aust.service;


import java.util.List;

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

    /**
     * 查看阅读量(初期文章阅读量低,所以直接根据cookies判断是否阅读过并写入库中)
     * @param article 该文章
     * @return 加一后的阅读量
     */
    int viewHits(Article article);
}
