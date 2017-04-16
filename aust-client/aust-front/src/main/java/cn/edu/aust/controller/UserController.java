package cn.edu.aust.controller;

import com.alibaba.fastjson.JSON;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aust.common.constant.RedisKey;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.service.SolutionService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.vo.UserInfoVO;
import cn.edu.aust.vo.UserRankVO;

/**
 * @author Niu Li
 * @since  2017/1/26
 */
@RestController
public class UserController {

  @Resource
  private UserService userService;
  @Resource
  private SolutionService solutionService;
  @Resource
  private StringRedisTemplate redisTemplate;

  /**
   * 获取首页展示用户
   */
  @GetMapping(value = "/index/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> indexToShow() {
    List<UserRankVO> userRanks = userService.queryToIndexShow()
        .stream().limit(6).map(UserRankVO::assemble)
        .collect(Collectors.toList());
    return new ResultVO<>().buildOKWithData(userRanks);
  }

  /**
   * 获取指定用户的基本信息
   * @return 用户详情,包含AC信息
   */
  @GetMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO userDetail(@PathVariable("id") Long id,HttpServletResponse response){
    ResultVO<UserInfoVO> resultVO = new ResultVO<>();
    UserDTO userDTO = userService.findById(id);
    if (Objects.isNull(userDTO)){
      return resultVO.buildOK();
    }
    //获取用户AC信息
    List<Integer> problemIds = solutionService.queryACProblems(id);
    //获取用户排名信息
    Long rank = redisTemplate.opsForZSet().reverseRank(RedisKey.RANK_USER,id.toString());
    UserInfoVO userInfoVO = UserInfoVO.assemble(userDTO, problemIds, rank);
    //写到cookie中
    WebUtils.addCookie(response,"currentUser", JSON.toJSONString(userInfoVO),-1,null, null,false);
    return resultVO.buildOKWithData(userInfoVO);
  }

  /**
   * 获取用户排名
   * 当前用户少,可以做到实时查询
   */
  @GetMapping(value = "/rank/users",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO rankUsers(){
    ResultVO<List<UserRankVO>> resultVO = new ResultVO<>();
    List<UserDTO> users = userService.queryForRank();
    return resultVO.buildOKWithData(UserRankVO.assemble(users));
  }

}
