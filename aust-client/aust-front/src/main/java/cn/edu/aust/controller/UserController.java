package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.edu.aust.assemble.UserVOAssemble;
import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.service.UserService;
import cn.edu.aust.vo.UserRankVO;

/**
 * @author Niu Li
 * @date 2017/1/26
 */
@RestController
public class UserController {

  @Resource
  private UserService userService;

  /**
   * 获取首页展示用户
   */
  @GetMapping(value = "/index/show/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> indexToShow() {
    List<UserRankVO> userRanks = userService.queryToIndexShow()
        .stream().limit(6)
        .map(UserVOAssemble::userDTO2RankVO)
        .collect(Collectors.toList());
    return new ResultVO<List>(PosCode.OK, userRanks);
  }
}
