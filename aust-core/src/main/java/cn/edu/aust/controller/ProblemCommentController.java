package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.edu.aust.common.ResultVo;
import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;
import cn.edu.aust.common.util.ReturnUtil;
import cn.edu.aust.service.ProblemCommentService;
import cn.edu.aust.service.ProblemService;
import cn.edu.aust.service.ReportLogService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.service.VoteLogService;

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
    @Resource(name = "reportLogServiceImpl")
    private ReportLogService reportLogService;
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Resource(name = "voteLogServiceImpl")
    private VoteLogService voteLogService;
    /**
     * 前往指定题目评论页
     * @param problem_id 对应题目的id
     * @return 该页面
     */
    @RequestMapping(value = "/{problem_id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String problemComment(@PathVariable(value = "problem_id") Integer problem_id,
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

    /**
     * 根据根节点评论查找旗下子评论
     * @param root_id 根节点
     * @param pageNum 分页参数
     * @param pageSize 分页参数
     * @return 评论html片段
     */
    @RequestMapping(value = "/detail/{root_id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String childrenComment(@PathVariable("root_id") Integer root_id,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        PageInfo<ProblemCommentUser> commentList = PageHelper.startPage(pageNum,pageSize)
                .doSelectPageInfo(
                        ()->problemCommentService.selectByRootId(root_id,10)
                );
        model.addAttribute("comments",commentList);
        return "fragment/discuss :: #discusscontent";
    }

    /**
     * 举报某个评论
     * @param id 该评论id
     * @return 举报的状态
     */
    @ResponseBody
    @RequestMapping(value = "/report/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject reportComment(@PathVariable("id") Integer id){
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        return reportLogService.reportProblemComment(result,id,user.getId());
    }

    /**
     * 点赞某个评论
     * @param id 该评论id
     * @param status 点的 向上还是向下箭头 1向上 其他向下
     * @return 点赞的状态
     */
    @ResponseBody
    @RequestMapping(value = "/vote/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject voteComment(@PathVariable("id") Integer id,
                                  @RequestParam(defaultValue = "1") Integer status){
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        return voteLogService.voteProblemComment(result,id,user.getId(),status);
    }

}
