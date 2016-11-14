package cn.edu.aust.common.util;

import com.alibaba.fastjson.JSONObject;

import cn.edu.aust.common.ResultVo;

/**
 * 构造ajax返回信息的工具类
 * @author Niu Li
 * @date 2016/10/18
 */
public final class ReturnUtil {
    /**
     * 包装枚举到JSONObject中
     * @param res JSONObject
     * @param resultVo 枚举
     * @return 包装后的JSONObject
     */
    public static JSONObject packingRes(JSONObject res, ResultVo resultVo){
        res.put("status",resultVo.getStatus());
        res.put("msg",resultVo.getMsg());
        return res;
    }

    /**
     * 包装枚举到JSONObject中
     * @param res JSONObject
     * @param resultVo 枚举
     * @param msg 附加信息
     * @return 包装后的JSONObject
     */
    public static JSONObject packingRes(JSONObject res, ResultVo resultVo,String msg){
        res.put("status",resultVo.getStatus());
        res.put("msg",resultVo.getMsg().concat(msg));
        return res;
    }

}
