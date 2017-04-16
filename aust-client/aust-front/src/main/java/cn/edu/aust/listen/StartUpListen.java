package cn.edu.aust.listen;

import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import cn.edu.aust.common.constant.RedisKey;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.service.UserService;

/**
 * 存放启动执行的任务
 * @author Niu Li
 * @since 2017/4/16
 */
@Component
public class StartUpListen {
  @Resource
  private UserService userService;
  @Resource
  private StringRedisTemplate redisTemplate;

  @PostConstruct
  public void init(){
    ranks2Redis();
  }

  /**
   * 用户排名信息放入到redis中
   */
  private void ranks2Redis(){
    List<UserDTO> userDTOS = userService.queryForRank();
    Set<ZSetOperations.TypedTuple<String>> sets = userDTOS.stream()
        .map(x -> new DefaultTypedTuple<>(x.getId().toString(), x.getSolved().doubleValue()))
        .collect(Collectors.toSet());
    redisTemplate.opsForZSet().add(RedisKey.RANK_USER,sets);
  }

}
