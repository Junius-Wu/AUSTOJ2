package cn.edu.aust.util;


import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.edu.aust.common.util.Filter;

/**
 * 使用apache-commom系列对数据进行检验,需要根据自己的项目来修改
 * @author Niu Li
 * @date 2016/9/15
 */
public class CheckParamUtil {

    /**
     * 检验map中的参数值
     * @param paramMaps 待检验的map
     * @param filters 过滤规则
     * @param res 错误信息放入json对象
     * @return 是否有错误
     */
    public static boolean checkParamMap(Map<String,Object> paramMaps, List<Filter> filters, JSONObject res){
        if (filters == null || filters.isEmpty()){
            return true;
        }

        for (Filter filter : filters) {
            if (filter == null){
                continue;
            }
            String property = filter.getProperty();
            Filter.Operator operator = filter.getOperator();
            Object value = filter.getValue();
            switch (operator){
                case isNotEmpty:
                    if (checkEmpty(paramMaps,property)){
                        return false;
                    }
                    break;
                case isEmpty:
                    if (!checkEmpty(paramMaps,property)){
                        return false;
                    }
                    break;
                case eq:
                    if(!checkEQ(paramMaps,property,value)){
                        return false;
                    }
                    break;
                case ne:
                    if(checkEQ(paramMaps,property,value)){
                        return false;
                    }
                    break;
                case gt:
                    if(!checkGT(paramMaps,property,value)){
                        return false;
                    }
                    break;
                case lt:
                    if(checkGT(paramMaps,property,value)){
                        return false;
                    }
                    break;
                case ge:
                    if(!checkGE(paramMaps,property,value)){
                        return false;
                    }
                    break;
                case le:
                    if(checkGE(paramMaps,property,value)){
                        return false;
                    }
                    break;
                case isNull:
                    if(!checkNull(paramMaps,property)){
                        return false;
                    }
                    break;
                case isNotNull:
                    if(checkNull(paramMaps,property)){
                        return false;
                    }
                    break;
                case in:
                    if(!checkIN(paramMaps,property,value)){
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    /**
     * 检测是否包含
     */
    private static boolean checkIN(Map<String,Object> paramMaps,String property,Object valueCheck){
        Object value = paramMaps.get(property);
        if (value == null){
            return false;
        }
        if (value instanceof String && valueCheck instanceof String){
            return StringUtils.contains((String)value,(String)valueCheck);
        }
        if (value instanceof Collection){
            return CollectionUtils.isSubCollection((Collection) value, (Collection) valueCheck);
        }
        return false;
    }
    /**
     * 检测是否为null许
     */
    private static boolean checkNull(Map<String,Object> paramMaps,String property){
        Object value = paramMaps.get(property);
        return value == null;
    }
    /**
     * 检测是否大于等于
     */
    private static boolean checkGE(Map<String,Object> paramMaps,String property,Object valueCheck){
        Object value = paramMaps.get(property);
        if (value == null){
            return true;
        }
        if (value instanceof Number && valueCheck instanceof Number){
            return ((Number) value).doubleValue() >= ((Number) valueCheck).doubleValue();
        }
        return false;
    }
    /**
     * 检测是否大于
     */
    private static boolean checkGT(Map<String,Object> paramMaps,String property,Object valueCheck){
        Object value = paramMaps.get(property);
        if (value == null){
            return true;
        }
        if (value instanceof Number && valueCheck instanceof Number){
            return ((Number) value).doubleValue() > ((Number) valueCheck).doubleValue();
        }
        return false;
    }
    /**
     * 检测是否相等
     */
    private static boolean checkEQ(Map<String,Object> paramMaps,String property,Object valueCheck){
        Object value = paramMaps.get(property);
        if (value == null){
            return true;
        }
        if (value instanceof Number && valueCheck instanceof Number){
            return ((Number) value).doubleValue() == ((Number) valueCheck).doubleValue();
        }
        if (value instanceof String && valueCheck instanceof String){
            return StringUtils.equals((String)value,(String)valueCheck);
        }
        if (value instanceof Collection && valueCheck instanceof Collection){
            return CollectionUtils.isEqualCollection((Collection) value, (Collection) valueCheck);
        }
        return false;
    }

    /**
     * 检测是否为空
     */
    private static boolean checkEmpty(Map<String,Object> paramMaps,String property){
        Object value = paramMaps.get(property);
        if (value == null){
            return true;
        }
        if (value instanceof String){
            return StringUtils.isEmpty((String)value);
        }
        if (value instanceof Collection){
            return CollectionUtils.isEmpty((Collection) value);
        }
        return false;
    }


}
