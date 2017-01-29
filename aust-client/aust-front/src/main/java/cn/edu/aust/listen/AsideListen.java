package cn.edu.aust.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.pojo.entity.Catelog;
import cn.edu.aust.pojo.entity.Tags;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.TagService;

/**
 * 侧边栏等全局访问,保存到application作用域
 * 需要提供刷新接口
 * @author Niu Li
 * @date 2017/1/29
 */
@Component
public class AsideListen implements ServletContextAware {

    private static Logger logger = LoggerFactory.getLogger(AsideListen.class);

    /** ServletContext */
    private ServletContext servletContext;
    @Autowired
    private CatelogService catelogService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 初始化方法
     */
    @PostConstruct
    public void init(){
        refreshCateLog();
        refreshArticle();
        refreshTag();
        logger.info("侧边栏写入完毕");
    }

    /**
     * 侧边目录
     */
    public void refreshCateLog(){
        List<Catelog> catelogs = catelogService.queryAll();
        servletContext.setAttribute("app_catelogs",catelogs);
        logger.info("侧边栏目录刷新完毕");
    }

    /**
     * 侧边栏文章
     */
    public void refreshArticle(){
        Setting setting = SystemUtil.getSetting(jedisClient);
        List<ArticleAsideDTO> articleAsideDTOS = articleService.queryForAside(setting.getAside_articles());
        servletContext.setAttribute("app_articles",articleAsideDTOS);
        logger.info("侧边栏文章刷新完毕");
    }

    /**
     * 刷新标签
     */
    public void refreshTag(){
        Setting setting = SystemUtil.getSetting(jedisClient);
        List<Tags> tagss = tagService.queryList(setting.getAside_tags());
        servletContext.setAttribute("app_tags",tagss);
        logger.info("侧边栏标签刷新完毕");
    }

}
