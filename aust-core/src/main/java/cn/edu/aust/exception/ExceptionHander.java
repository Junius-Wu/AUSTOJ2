package cn.edu.aust.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        Throwable throwable = exception.getCause();
        modelAndView.addObject("errorMessage", throwable);
        logger.error(throwable.toString(), exception);
        return modelAndView;
    }
}
