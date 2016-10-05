package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.Principal;
import cn.edu.aust.Setting;
import cn.edu.aust.entity.Article;
import cn.edu.aust.entity.Catelog;
import cn.edu.aust.entity.Tag;
import cn.edu.aust.entity.User;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.TagService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.SystemUtil;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Controller
@RequestMapping(value = "/frg")
public class FragmentController {

    @Resource(name = "catelogServiceImpl")
    private CatelogService catelogService;
    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;
    @Resource(name = "userServiceImpl")
    private UserService userService;
    /**
     * 返回侧边栏,配合ajax返回html
     * @return
     */
    @RequestMapping(value = "/aside",method = RequestMethod.GET)
    public String asideFragment(@SessionAttribute(name ="userpri") Principal principal, Model model){
        Setting setting = SystemUtil.getSetting();
        if (principal != null){
            User user = userService.selectByPrimaryKey(principal.getId());
            model.addAttribute("user",user);

        }
        List<Catelog> catelogs = catelogService.selectList(0);
        List<Tag> tags = tagService.selectList(setting.getAside_tags());
        List<Article> articles = articleService.selectList(setting.getAside_articles());
        model.addAttribute("catelogs",catelogs);
        model.addAttribute("tags",tags);
        model.addAttribute("articles",articles);
        return "fragment/aside :: #asideFragment";
    }

}
