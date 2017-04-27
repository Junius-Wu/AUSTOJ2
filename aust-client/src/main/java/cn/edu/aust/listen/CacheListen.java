package cn.edu.aust.listen;

import com.google.common.collect.Maps;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.service.CatelogService;

/**
 * 缓存一些代码数据
 * @author Niu Li
 * @since 2017/4/26
 */
@Component
public class CacheListen implements InitializingBean{

  public static final Map<Integer,CatelogDTO> CATELOG_CACHE = Maps.newConcurrentMap();

  @Resource
  private CatelogService catelogService;

  /**
   * 缓存目录
   */
  private void cacheCatelogs(){
    List<CatelogDTO> catelogDTOS = catelogService.queryAll();
    if (!CollectionUtils.isEmpty(catelogDTOS)) {
      CATELOG_CACHE.putAll(catelogDTOS.stream()
          .collect(Collectors.toConcurrentMap(CatelogDTO::getId, Function.identity()))
      );
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.cacheCatelogs();
  }
}
