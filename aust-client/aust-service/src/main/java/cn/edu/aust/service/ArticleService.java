package cn.edu.aust.service;

import com.github.pagehelper.PageHelper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.mapper.ArticleMapper;
import cn.edu.aust.pojo.entity.Article;

/**
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class ArticleService extends BaseService<Article> {
    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 查询侧边栏显示的文章
     * @param limit 数量
     * @return 映射集合
     */
    public List<ArticleAsideDTO> queryForAside(Integer limit){
        PageHelper.offsetPage(0,limit,false);
        List<Article> articles = articleMapper.queryForAside();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(articles,new TypeToken<List<ArticleAsideDTO>>(){}.getType());
    }
}
