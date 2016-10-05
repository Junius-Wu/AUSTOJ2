package cn.edu.aust.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.entity.Article;
import cn.edu.aust.entity.ArticleBLOBs;
import cn.edu.aust.mapper.ArticleMapper;
import cn.edu.aust.service.ArticleService;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

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
