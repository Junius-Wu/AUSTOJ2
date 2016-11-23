package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
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
import cn.edu.aust.common.util.StringUtils;
import cn.edu.aust.exception.PageException;
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
@RequestMapping("/comment/pro")
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
     * @param isOrder 对应题目的id
     * @return 该页面
     */
    @RequestMapping(value = "/{problem_id}",method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    public String problemComment(@PathVariable(value = "problem_id") Integer problem_id,
                                 @RequestParam(defaultValue = "0") Integer isOrder,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "6") Integer pageSize, Model model){
        PageInfo<ProblemCommentUser> pageInfo =
                PageHelper.startPage(pageNum,pageSize)
                          .doSelectPageInfo(
                                  ()->problemCommentService.selectToShow(problem_id,10,isOrder>0)
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
    @RequestMapping(value = "/detail/{root_id}",method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    public String childrenComment(@PathVariable("root_id") Integer root_id,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        PageInfo<ProblemCommentUser> commentList =
                PageHelper.startPage(pageNum,pageSize)
                          .doSelectPageInfo(
                                ()->problemCommentService.selectByRootId(root_id,10)
                          );
        model.addAttribute("comments",commentList);
        model.addAttribute("root_id",root_id);
        return "fragment/discuss :: #discusscontent";
    }

    /**
     * 删除某个评论
     * @param id 该评论id
     * @return 删除成功或没权限
     */
    @ResponseBody
    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject deleteComment(@PathVariable("id") Integer id){
       JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        boolean isSuccess = problemCommentService.deleteByPKAndUser(id,user);
        return ReturnUtil.packingRes(result,isSuccess?ResultVo.OK:ResultVo.NO_PRIVILEGE);
    }

    /**
     * 修改某个评论内容
     * @param id 评论id
     * @param content 评论内容
     * @return 修改成功或没权限
     */
    @ResponseBody
    @RequestMapping(value = "/modify/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject modifyComment(@PathVariable("id") Integer id,String content){
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        boolean isSuccess = problemCommentService.updateByPKWithBLOBs(id,content,user);
        result.put("content",content);
        return ReturnUtil.packingRes(result,isSuccess?ResultVo.OK:ResultVo.NO_PRIVILEGE);
    }

    /**
     * 添加评论(非回复)
     * @param problem_id 对应题目的id
     * @return 成功或异常信息
     */
    @RequestMapping(value = "/add/{problem_id}",method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    public String addComment(@PathVariable("problem_id") Integer problem_id,String content, Model model){
        User user = userService.getCurrent();
        if (user == null){
            model.addAttribute("errorMessage",ResultVo.NOT_LOGIN.getMsg());
            return "error";
        }
        boolean isSuccess = problemCommentService.insert(problem_id,content,false,0,user);
        if (!isSuccess){
            model.addAttribute("errorMessage",ResultVo.NO_PRIVILEGE.getMsg());
            return "error";
        }
        return "redirect:/comment/pro/"+problem_id+"?isOrder=1";
    }

    /**
     * 回复评论
     * @param root_id 题目id
     * @param content 回复内容
     * @param friendId 对方id,不存在则回复root评论
     */
    @ResponseBody
    @RequestMapping(value = "/reply/{root_id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject replyComment(@PathVariable("root_id") Integer root_id,String content,
                                   @RequestParam(defaultValue = "0") Integer friendId) throws PageException {
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        if (StringUtils.isEmpty(content)){
            return ReturnUtil.packingRes(result,ResultVo.EMPTY_ERROR);
        }
        boolean isSuccess = problemCommentService.insertReply(root_id,content,true,friendId,user);
        result.put("content",content);
        return ReturnUtil.packingRes(result,isSuccess?ResultVo.OK:ResultVo.COMMENT_ERROR);
    }
    /**
     * 举报某个评论
     * @param root_id 该评论id
     * @return 举报的状态
     */
    @ResponseBody
    @RequestMapping(value = "/report/{root_id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject reportComment(@PathVariable("root_id") Integer root_id){
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        reportLogService.reportProblemComment(result,root_id,user.getId());
        return ReturnUtil.packingRes(result,ResultVo.OK);
    }

    /**
     * 点赞某个评论
     * @param root_id 该评论id
     * @param status 点的 向上还是向下箭头 1向上 其他向下
     * @return 点赞的状态
     */
    @ResponseBody
    @RequestMapping(value = "/vote/{root_id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject voteComment(@PathVariable("root_id") Integer root_id,
                                  @RequestParam(defaultValue = "1") Integer status){
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        voteLogService.voteProblemComment(result,root_id,user.getId(),status);
        return ReturnUtil.packingRes(result,ResultVo.OK);
    }

}
