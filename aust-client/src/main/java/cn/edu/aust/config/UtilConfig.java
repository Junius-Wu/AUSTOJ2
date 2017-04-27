package cn.edu.aust.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/** 常用配置类
 * @author Niu Li
 * @since 2017/4/27
 */
@Component
public class UtilConfig {

  /**
   * 通用型modelmapper,不适用于自定义配置
   * 自定义的直接new
   */
  @Bean
  public static ModelMapper configModelMapper(){
    return new ModelMapper();
  }



}
