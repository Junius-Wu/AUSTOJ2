package cn.edu.aust.exception;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Niu Li
 * @date 2016/9/4
 */
@ControllerAdvice
public class ExceptionHander{

    private static Logger logger = LoggerFactory.getLogger(ExceptionHander.class);

    /**
     * Handle exceptions thrown by handlers.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        exception.printStackTrace();
        String requestType = request.getHeader("x-requested-with");
        ModelAndView modelAndView = new ModelAndView("error");
        Throwable throwable = exception.getCause();
        modelAndView.addObject("errorMessage", throwable);
        logger.error(throwable.toString(), exception);
        //为ajax请求
        if (StringUtils.equals(requestType,"XMLHttpRequest")){
            modelAndView.getModel().put("status",500);
            response.getWriter().write(JSON.toJSONString(modelAndView.getModel()));
            return null;
        }
        return modelAndView;
    }
}
