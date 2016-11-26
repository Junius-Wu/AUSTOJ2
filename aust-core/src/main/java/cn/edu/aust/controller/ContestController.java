package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.ResultVo;
import cn.edu.aust.common.entity.Contest;
import cn.edu.aust.common.entity.ContestProblem;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.util.ReturnUtil;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.ContestProblemService;
import cn.edu.aust.service.ContestService;
import cn.edu.aust.service.UserService;

/**
 * 竞赛控制器
 * @author Niu Li
 * @date 2016/11/26
 */
@Controller
@RequestMapping("/contest")
public class ContestController {
    @Resource(name = "contestServiceImpl")
    private ContestService contestService;
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Resource(name = "contestProblemServiceImpl")
    private ContestProblemService contestProblemService;
    /**
     * 前往竞赛页面
     */
    @RequestMapping(value = "",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String contest(Model model){
        List<Contest> contests = contestService.selectDefunct(false);//有效
        List<Contest> overContests = contestService.selectDefunct(true);//过期
        model.addAttribute("overContests",overContests);
        model.addAttribute("contests",contests);
        return "contest";
    }

    /**
     * 查看具体竞赛内容
     * @param id 竞赛id
     * @return 该视图
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String goContestDetail(@PathVariable("id") Integer id,HttpSession session,Model model) throws PageException {
        User user = userService.getCurrent();
        if (user == null){
            throw new PageException(ResultVo.NO_PRIVILEGE);
        }
        Integer access = (Integer) session.getAttribute(Contest.SESSION_ACCESS);
        if (access == null){
            throw new PageException(ResultVo.NO_PRIVILEGE);
        }
        if (access != user.getId()+id){
            throw new PageException(ResultVo.NO_PRIVILEGE);
        }
        //得到详细内容
        List<ContestProblem> problemList = contestProblemService.selectByContestId(id);
        model.addAttribute("problems",problemList);
        return "contestdetail";
    }

    /**
     * 检查是否可以访问该竞赛
     * @param id 竞赛id
     * @param passwd 密码
     * @return 检查结果
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject contestDetail(@PathVariable("id") Integer id, String passwd, HttpSession session){
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }
        boolean isSuccess = contestService.judgeCanAccess(id,passwd,result);
        //成功后存入一个标识
        if (isSuccess){
            session.setAttribute(Contest.SESSION_ACCESS,user.getId()+id);
            ReturnUtil.packingRes(result, ResultVo.OK);
        }
        return result;
    }

}
