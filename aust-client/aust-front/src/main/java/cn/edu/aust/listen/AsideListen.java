package cn.edu.aust.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.pojo.entity.CatelogDO;
import cn.edu.aust.pojo.entity.NotifyDO;
import cn.edu.aust.pojo.entity.TagsDO;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.NotifyService;
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
    @Resource
    private CatelogService catelogService;
    @Resource
    private ArticleService articleService;
    @Resource
    private TagService tagService;
    @Resource
    private NotifyService notifyService;
    @Resource
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
        jedisClient.del(SystemUtil.SETTING_CACHE);
        refreshCateLog();
        refreshArticle();
        refreshTag();
        logger.info("侧边栏写入完毕");
        refreshNotify();
        logger.info("通知写入完毕");
    }

    /**
     * 获取通知
     */
    public void refreshNotify(){
        Setting setting = SystemUtil.getSetting(jedisClient);
        List<NotifyDO> notifies = notifyService.queryListNow(setting.getNotify_count());
        servletContext.setAttribute("app_notifys",notifies);
        logger.info("网站通知刷新完毕");
    }
    /**
     * 侧边目录
     */
    public void refreshCateLog(){
        List<CatelogDO> catelogDOS = catelogService.queryAll();
        servletContext.setAttribute("app_catelogs", catelogDOS);
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
        List<TagsDO> tagsDOS = tagService.queryList(setting.getAside_tags());
        servletContext.setAttribute("app_tags", tagsDOS);
        logger.info("侧边栏标签刷新完毕");
    }

}
