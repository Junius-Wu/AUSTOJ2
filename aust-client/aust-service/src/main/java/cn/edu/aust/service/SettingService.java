package cn.edu.aust.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;

/**
 * todo 等待迁移
 * 系统设置服务
 * @author Niu Li
 * @since 2017/3/19
 */
@Service
public class SettingService {
  
  @Resource
  private JedisClient jedisClient;

  /**
   * 读取配置
   * @return 系统配置
   */
  public Setting getSetting(){
    return SystemUtil.getSetting(jedisClient);
  }
}
