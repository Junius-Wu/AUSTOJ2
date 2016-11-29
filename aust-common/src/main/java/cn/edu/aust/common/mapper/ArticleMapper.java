package cn.edu.aust.common.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.ArticleBLOBs;
import cn.edu.aust.common.entity.pojo.ArticleUser;

public interface ArticleMapper {
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
     * @param curentUser 当前用户,判断是否点赞过
     * @param search 搜索字段
     * @return 查询集合
     */
    List<ArticleUser> selectAll(@Param("curentUser") Integer curentUser,@Param("search") String search);

    /**
     * 查找单条文章记录
     * @param curentUser 当前用户,判断是否点赞过
     * @param articleId 文章id
     * @return 查询实体
     */
    ArticleUser selectByPk(@Param("curentUser") Integer curentUser,@Param("articleId") Integer articleId);
}