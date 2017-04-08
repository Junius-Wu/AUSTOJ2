package cn.edu.aust.listen;

import com.alibaba.fastjson.JSON;

import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

import cn.edu.aust.common.entity.MessageTypeDO;
import lombok.extern.slf4j.Slf4j;

/**
 * 判题消息监听处理
 * 目前内容存在Application中因此需要放在controller中
 * @author Niu Li
 * @since 2017/3/31
 */
@Slf4j
public class JudgerMessageListen implements ServletContextAware {

  /**
   * ServletContext
   */
  private ServletContext servletContext;

  /**
   * 处理消息
   */
  public void handle(String msgType){
    log.info("receive msgType is {}",msgType);
    MessageTypeDO msgTypeDO = null;
    try {
      msgTypeDO = JSON.parseObject(msgType,MessageTypeDO.class);
    } catch (Exception e) {
      log.error("judger msg handel failed,msgType is {}",msgType);
      return;
    }
    //根据消息类型处理
    switch (msgTypeDO.getType()){

    }
  }

  /**
   * 注入方法
   */
  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
}
