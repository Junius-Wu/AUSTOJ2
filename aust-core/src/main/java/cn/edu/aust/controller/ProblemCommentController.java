package cn.edu.aust.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;
import cn.edu.aust.service.ProblemCommentService;
import cn.edu.aust.service.ProblemService;

/**
 * 题目评论的控制器
 * @author Niu Li
 * @date 2016/11/13
 */
@Controller
@RequestMapping("/commont/pro")
public class ProblemCommentController {

    @Resource(name = "problemCommentServiceImpl")
    private ProblemCommentService problemCommentService;
    @Resource(name = "problemServiceImpl")
    private ProblemService problemService;

    /**
     * 前往指定题目评论页
     * @param problem_id 对应题目的id
     * @return 该页面
     */
    @RequestMapping(value = "/{problem_id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String problemCmmont(@PathVariable(value = "problem_id") Integer problem_id,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        PageInfo<ProblemCommentUser> pageInfo = PageHelper.startPage(pageNum,pageSize)
                .doSelectPageInfo(
                        ()->problemCommentService.selectToShow(problem_id,10)
                );
        Problem problem = problemService.selectBaseByPk(problem_id);
        model.addAttribute("pageinfo",pageInfo);
        model.addAttribute("problem",problem);
        return "pro-discuss";
    }

}
