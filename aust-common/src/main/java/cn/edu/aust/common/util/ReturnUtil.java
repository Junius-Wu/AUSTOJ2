package cn.edu.aust.common.util;

import com.alibaba.fastjson.JSONObject;

import cn.edu.aust.common.ResultVo;

/**
 * 构造ajax返回信息的工具类
 * @author Niu Li
 * @date 2016/10/18
 */
public final class ReturnUtil {

    public static JSONObject packingRes(JSONObject res, ResultVo resultVo){
        res.put("status",resultVo.getStatus());
        res.put("msg",resultVo.getMsg());
        return res;
    }
    public static JSONObject packingRes(JSONObject res, ResultVo resultVo,String msg){
        res.put("status",resultVo.getStatus());
        res.put("msg",resultVo.getMsg().concat(msg));
        return res;
    }

}
