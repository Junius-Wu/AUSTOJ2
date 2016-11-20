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

    /**
     * 判断两者是否相等
     * @param cs1 字串1
     * @param cs2 字串2
     * @return true相等
     */
    public static boolean equals(CharSequence cs1,CharSequence cs2){
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return false;
    }
}
