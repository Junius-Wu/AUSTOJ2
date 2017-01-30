package cn.edu.aust.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.dto.ArticleListDTO;
import cn.edu.aust.mapper.ArticleMapper;
import cn.edu.aust.pojo.entity.Article;
import cn.edu.aust.pojo.entity.User;
import cn.edu.aust.query.ArticlePC;
import cn.edu.aust.query.ArticleQM;

/**
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class ArticleService extends BaseService<Article> {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserService userService;

    /**
     * 根据主键查询展示对象
     * @param id 主键
     * @return 详情展示对象
     */
    public ArticleDTO queryDetail(Long id){
        ArticlePC article = articleMapper.queryDetail(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(article,ArticleDTO.class);
    }

    @Transactional
    public int viewHits(ArticleDTO articleDTO) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        Long id = articleDTO.getId();
        String articles = WebUtils.getCookie(request, ArticleDTO.ARTICLEHIT_COOKIES);
        boolean canAdd = false;
        //不存在则添加
        if (articles == null){
            WebUtils.addCookie(response, ArticleDTO.ARTICLEHIT_COOKIES,id+",",24*3600,"/",null,false);
            canAdd = true;
        }else {
            //cookies中不存在则添加
            if (articles.contains(id+",")){
                canAdd = false;
            }else {
                String value = articles+","+id + ",";
                WebUtils.addCookie(response, ArticleDTO.ARTICLEHIT_COOKIES, value,24*3600,"/",null,false);
                canAdd = true;
            }
        }
        if (canAdd){
            Article article = new Article();
            article.setId(articleDTO.getId());
            int viewcount = articleDTO.getViewcount() + 1;
            article.setViewcount(viewcount);
            updateSelective(article);
            return viewcount;
        }
        return articleDTO.getViewcount();
    }

    /**
     * 查询侧边栏显示的文章
     *
     * @param limit 数量
     * @return 映射集合
     */
    public List<ArticleAsideDTO> queryForAside(Integer limit) {
        PageHelper.offsetPage(0, limit, false);
        List<Article> articles = articleMapper.queryForAside();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(articles, new TypeToken<List<ArticleAsideDTO>>() {
        }.getType());
    }

    /**
     * 查询文章展示类
     *
     * @param pageNum 页码
     * @param limit   每页数量
     * @return 结果集
     */
    public PageInfo<ArticleListDTO> queryList(String search, Integer pageNum, Integer limit) {
        //查询条件
        ArticleQM articleQM = new ArticleQM();
        articleQM.setSearch(search);
        User user = userService.getCurrent();
        if (user != null) {
            articleQM.setUserId(user.getId());
        }
        //分页查询并转换结果
        PageInfo<ArticlePC> articlePCS = PageHelper.startPage(pageNum, limit).doSelectPageInfo(
                                                                        () -> {
                                                                            articleMapper.queryList(articleQM);
                                                                        }
                                                                );
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        //针对内部list的转换
        Converter<ArrayList<ArticlePC>,ArrayList<ArticleListDTO>> converter = new AbstractConverter<ArrayList<ArticlePC>, ArrayList<ArticleListDTO>>() {
            @Override
            protected ArrayList<ArticleListDTO> convert(ArrayList<ArticlePC> source) {
                return modelMapper.map(source,new TypeToken<ArrayList<ArticleListDTO>>(){}.getType());
            }
        };
        PropertyMap<PageInfo<ArticlePC>,PageInfo<ArticleListDTO>> propertyMap = new PropertyMap<PageInfo<ArticlePC>, PageInfo<ArticleListDTO>>() {
            @Override
            protected void configure() {
                using(converter).map(source.getList(),destination.getList());
            }
        };
        modelMapper.addMappings(propertyMap);
        modelMapper.createTypeMap(ArticlePC.class,ArticleListDTO.class);
        return modelMapper.map(articlePCS, new TypeToken<PageInfo<ArticleListDTO>>() {}.getType());
    }
}
