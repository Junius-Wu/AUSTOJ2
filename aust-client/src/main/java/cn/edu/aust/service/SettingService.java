package cn.edu.aust.service;

import com.alibaba.fastjson.JSON;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.util.SystemUtil;

/**
 * 系统设置服务
 * @author Niu Li
 * @since 2017/3/19
 */
@Service
public class SettingService {

  public static final String SETTING_CACHE = "aust-setting";

  @Resource
  private StringRedisTemplate redisTemplate;

  /**
   * 读取配置 缓存>配置
   * @return 系统配置
   */
  public Setting getSetting(){
    String settingCache = redisTemplate.opsForValue().get(SETTING_CACHE);
    if (Objects.isNull(settingCache)){
      return SystemUtil.getSetting();
    }
    Setting setting = JSON.parseObject(settingCache,Setting.class);
    if (Objects.nonNull(setting)){
      return setting;
    }
    return SystemUtil.getSetting();
  }

}
