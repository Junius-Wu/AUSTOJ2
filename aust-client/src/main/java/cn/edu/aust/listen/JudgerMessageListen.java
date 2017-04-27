package cn.edu.aust.listen;

import com.alibaba.fastjson.JSON;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.MessageKey;
import cn.edu.aust.common.constant.RedisKey;
import cn.edu.aust.common.entity.MessageType;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 判题消息监听处理
 * @author Niu Li
 * @since 2017/3/31
 */
@Slf4j
public class JudgerMessageListen {
  @Resource
  private UserService userService;
  @Resource
  private StringRedisTemplate redisTemplate;

  /**
   * 处理消息
   */
  public void handle(String msgType){
    log.info("receive msgType is {}",msgType);
    MessageType msgTypeDO = null;
    try {
      msgTypeDO = JSON.parseObject(msgType,MessageType.class);
    } catch (Exception e) {
      log.error("judger msg handel failed,msgType is {}",msgType);
      return;
    }
    //根据消息类型处理
    switch (msgTypeDO.getType()){
      case MessageKey.JUDGER_RESULT:
        handleJudgerResult(msgTypeDO.getSubjectId(),msgTypeDO.getObjectId());
        break;
    }
  }

  /**
   * 处理判题事件
   */
  public void handleJudgerResult(Long userId,Long problemId){
    UserDTO userDTO = userService.findById(userId);
    //更新用户排名信息
    redisTemplate.opsForZSet().add(RedisKey.RANK_USER,userId.toString(),userDTO.getSolved());
  }

}
