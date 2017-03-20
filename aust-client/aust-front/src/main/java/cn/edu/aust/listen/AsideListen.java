package cn.edu.aust.listen;

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
import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.pojo.entity.NotifyDO;
import cn.edu.aust.pojo.entity.TagsDO;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.NotifyService;
import cn.edu.aust.service.SettingService;
import cn.edu.aust.service.TagService;
import lombok.extern.slf4j.Slf4j;

/**
 * 侧边栏等全局访问,保存到application作用域
 * 需要提供刷新接口
 * @author Niu Li
 * @date 2017/1/29
 */
@Component
@Slf4j
public class AsideListen implements ServletContextAware {

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
    @Resource
    private SettingService settingService;

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
        refreshNotify();
    }

    /**
     * 获取通知
     */
    public void refreshNotify(){
        Setting setting = settingService.getSetting();
        List<NotifyDO> notifies = notifyService.queryListNow(setting.getNotify_count());
        servletContext.setAttribute("app_notifys",notifies);
        log.info("refreshNotify end");
    }
    /**
     * 侧边目录
     */
    public void refreshCateLog(){
        List<CatelogDTO> catelogDOS = catelogService.queryAll();
        servletContext.setAttribute("app_catelogs", catelogDOS);
        log.info("refreshCateLog end");
    }

    /**
     * 侧边栏文章
     */
    public void refreshArticle(){
        Setting setting = settingService.getSetting();
        List<ArticleAsideDTO> articleAsideDTOS = articleService.queryForAside(setting.getAside_articles());
        servletContext.setAttribute("app_articles",articleAsideDTOS);
        log.info("refreshArticle end");
    }

    /**
     * 刷新标签
     */
    public void refreshTag(){
        Setting setting = settingService.getSetting();
        List<TagsDO> tagsDOS = tagService.queryList(setting.getAside_tags());
        servletContext.setAttribute("app_tags", tagsDOS);
        log.info("refreshTag end");
    }

}
