package cn.edu.aust.common.service;

/**
 * jedis工具,在其他模块中使用依赖注入
 * @author Niu Li
 * @date 2017/1/22
 */
public interface JedisClient {
    /**
     * 获取字符串类型
     * @param key 指定key
     * @return 不存在则为null
     */
    String get(String key);

    /**
     * 设置Str值
     * @param key 指定key
     * @param value 指定值
     * @return 成功返回ok
     */
    String set(String key, String value);

    /**
     * 不存在则set
     * @param key 键
     * @param value 值
     * @return 1成功
     */
    Long setnx(final String key, final String value);

    /**
     * 设置并加时间
     * @param key 键
     * @param seconds 存活时间
     * @param value 值
     * @return ok成功
     */
    String setex(final String key, final int seconds, final String value);

    /**
     * 判断是否存在
     * @param key 键
     * @return true 存在
     */
    Boolean exists(String key);

    /**
     * 删除指定键
     * @param key 键
     * @return 大于0成功
     */
    Long del(String key);

    /**
     * 延时
     * @param key 键
     * @param seconds 秒数
     * @return 1成功
     */
    Long expire(final String key, final int seconds);
}
