package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.edu.aust.convert.UserVOAssemble;
import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.service.SolutionService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.vo.UserInfoVO;
import cn.edu.aust.vo.UserRankVO;

/**
 * @author Niu Li
 * @date 2017/1/26
 */
@RestController
public class UserController {

  @Resource
  private UserService userService;
  @Resource
  private SolutionService solutionService;

  /**
   * 获取首页展示用户
   */
  @GetMapping(value = "/index/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> indexToShow() {
    List<UserRankVO> userRanks = userService.queryToIndexShow()
        .stream().limit(6)
        .map(UserVOAssemble::userDTO2RankVO)
        .collect(Collectors.toList());
    return new ResultVO<List>(PosCode.OK, userRanks);
  }

  /**
   * 获取指定用户的基本信息
   * @return 用户详情,包含AC信息
   */
  @GetMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO userDetail(@PathVariable("id") Long id){
    ResultVO<UserInfoVO> resultVO = new ResultVO<>();
    UserDTO userDTO = userService.findById(id);
    if (Objects.isNull(userDTO)){
      return resultVO.buildOK();
    }
    List<Integer> problemIds = solutionService.queryACProblems(id);
    return resultVO.buildOKWithData(UserInfoVO.assemble(userDTO,problemIds));
  }

}
