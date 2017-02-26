package cn.edu.aust.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;


/**
 * @author Niu Li
 * @date 2016/9/4
 */
@Slf4j
public class ExceptionHander implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object o,
                                         Exception exception) {
        String requestType = request.getHeader("x-requested-with");
        ModelAndView modelAndView = new ModelAndView();
        Throwable throwable = exception.getCause();
        modelAndView.addObject("errorMessage", throwable);
        log.error("捕捉到异常", exception);
        //为ajax请求
        if (StringUtils.equals(requestType,"XMLHttpRequest")){
            JSONObject result = new JSONObject();
            result.put("status",96);
            result.put("msg",exception.getMessage());
            log.info(result.toJSONString());
            try {
                response.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}
