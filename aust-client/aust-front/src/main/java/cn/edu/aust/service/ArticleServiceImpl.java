package cn.edu.aust.service;


import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aust.common.entity.pojo.ArticleUser;
import cn.edu.aust.common.mapper.ArticleMapper;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.WEBUtil;

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

    @Override
    public int viewHits(Article article) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        Integer id = article.getId();
        String articles = WEBUtil.getCookie(request,Article.ARTICLEHIT_COOKIES);
        boolean canAdd = false;
        //不存在则添加
        if (articles == null){
            WEBUtil.addCookie(request,response,Article.ARTICLEHIT_COOKIES,id+",",24*3600,"/",null,false);
            canAdd = true;
        }else {
            //cookies中不存在则添加
            if (articles.contains(id+",")){
                canAdd = false;
            }else {
                String value = articles+","+id + ",";
                WEBUtil.addCookie(request,response,Article.ARTICLEHIT_COOKIES, value,24*3600,"/",null,false);
                canAdd = true;
            }
        }
        if (canAdd){
            ArticleBLOBs tempArticle = new ArticleBLOBs();
            tempArticle.setId(id);
            int viewcount = article.getViewcount() + 1;
            tempArticle.setViewcount(viewcount);
            articleMapper.updateByPrimaryKeySelective(tempArticle);
            return viewcount;
        }
        return article.getViewcount();
    }
}
