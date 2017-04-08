package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.dto.NotifyDTO;
import cn.edu.aust.service.NotifyService;
import lombok.extern.slf4j.Slf4j;

/**
 * 站点通知
 * @author Niu Li
 * @since 2017/4/8
 */
@RestController
@Slf4j
public class NotifyController {
  @Resource
  private NotifyService notifyService;

  private static final Integer showLimit = 10;
  /**
   * 获取站点当前有效期内通知
   * @return 通知
   */
  @GetMapping(value = "/tips",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO tips(){
    ResultVO<List<NotifyDTO>> resultVO = new ResultVO<>();
    List<NotifyDTO> notifyDOS = notifyService.queryListNow(showLimit);
    return resultVO.buildOKWithData(notifyDOS);
  }

}
