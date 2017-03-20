package cn.edu.aust.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Niu Li
 * @date 2016/9/4
 */
@Slf4j
@ControllerAdvice
public class ExceptionHander {

  /**
   * 全局异常处理
   *
   * @param request  请求
   * @param response 返回
   * @param ex       异常
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.OK)//前端不支持处理非200的异常
  @ResponseBody
  public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    log.error("ExceptionHander catch error: ", ex);
    //判断是否为ajax请求
    String xRequested = CgiHelper.getHeader("x-requested-with",null,request);
    if (StringUtils.equalsIgnoreCase(xRequested,"XMLHttpRequest")){
      return handlerAjax(ex);
    }

    //重定向到错误页面
    redirect("/error",HttpStatus.NOT_FOUND,response);
    return null;
  }

  /**
   * 处理ajax异常
   * @param ex 异常
   * @return json异常信息
   */
  private ResultVO handlerAjax(Exception ex){
    ResultVO resultVO = new ResultVO();
    return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,ex.getMessage());
  }

  /**
   * 重定向到错误页面
   * @param url 链接
   */
  private void redirect(String url,HttpStatus status, HttpServletResponse response){
    try {
      response.setStatus(status.value());
      response.sendRedirect(url);
    } catch (IOException e) {
      log.error("redirect fail,e:{}",e);
    }
  }
}
