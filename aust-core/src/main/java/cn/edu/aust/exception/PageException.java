package cn.edu.aust.exception;

import cn.edu.aust.ResultVo;

/**
 * @author Niu Li
 * @date 2016/9/11
 */
public class PageException extends Exception{

    public PageException(String message) {
        super(message);
    }

    public PageException(ResultVo res){
        super(res.getMsg());
    }
}
