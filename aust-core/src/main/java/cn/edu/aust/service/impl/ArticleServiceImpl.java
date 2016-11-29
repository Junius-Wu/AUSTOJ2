package cn.edu.aust.service.impl;


import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.ArticleBLOBs;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.entity.pojo.ArticleUser;
import cn.edu.aust.common.mapper.ArticleMapper;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.UserService;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Override
    public List<ArticleUser> selectAll(User user, String search) {
        return articleMapper.selectAll(user == null?null:user.getId(),search);
    }

    @Override
    public ArticleUser selectByPk(User user, Integer articleId) {
        return articleMapper.selectByPk(user == null?null:user.getId(),articleId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ArticleBLOBs record) {
        return articleMapper.insert(record);
    }

    @Override
    public int insertSelective(ArticleBLOBs record) {
        return articleMapper.insertSelective(record);
    }

    @Override
    public ArticleBLOBs selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ArticleBLOBs record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ArticleBLOBs record) {
        return articleMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Article> selectList(Integer count) {
        return articleMapper.selectList(count);
    }
}
