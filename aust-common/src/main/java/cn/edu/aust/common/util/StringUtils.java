package cn.edu.aust.common.util;

/**
 * 该项目使用的StringUtils
 * 目的学习common-lang3的写法
 * @author Niu Li
 * @date 2016/11/4
 */
public final class StringUtils {
    /**
     * 判断该字串是否为null或者空
     * @param cs 要判断的字串
     * @return true 空 false 不为空
     */
    public static boolean isEmpty(final CharSequence cs){
        return cs == null || cs.length() == 0;
    }
}
