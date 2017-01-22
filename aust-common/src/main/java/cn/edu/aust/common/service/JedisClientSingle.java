package cn.edu.aust.common.service;

import cn.edu.aust.common.util.WorkCallBack;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Niu Li
 * @date 2017/1/22
 */
public class JedisClientSingle implements JedisClient {

    /**
     * 连接池配置类,需要依赖注入
     */
    private JedisPool jedisPool;

    public JedisClientSingle(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    @Override
    public String get(String key) {
        return execute(jedis -> jedis.get(key));
    }

    @Override
    public String set(String key, String value) {
        return execute(jedis -> jedis.set(key,value));
    }

    @Override
    public Long setnx(String key, String value) {
        return execute(jedis -> jedis.setnx(key,value));
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return execute(jedis -> jedis.setex(key,seconds,value));
    }

    @Override
    public Boolean exists(String key) {
        return execute(jedis -> jedis.exists(key));
    }

    @Override
    public Long del(String key) {
        return execute(jedis -> jedis.del(key));
    }

    @Override
    public Long expire(String key, int seconds) {
        return execute(jedis -> jedis.expire(key,seconds));
    }

    /**
     * 内部执行
     * @param callBack 回调方法
     * @param <R> 返回类型
     * @return 返回类型
     */
    private <R> R execute(WorkCallBack<R,Jedis> callBack){
        try(Jedis jedis = jedisPool.getResource()){
            return callBack.execute(jedis);
        }
    }
}
