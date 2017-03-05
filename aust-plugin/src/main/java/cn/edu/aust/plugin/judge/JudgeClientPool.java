package cn.edu.aust.plugin.judge;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import cn.edu.aust.common.service.WorkCallBack;
import lombok.extern.slf4j.Slf4j;

/**
 * 判题连接池,调用方使用依赖注入
 * @author Niu Li
 * @since 2017/3/5
 */
@Slf4j
public class JudgeClientPool {
  private GenericObjectPool<JudgeClient> objectPool = null;


  public JudgeClientPool(Integer maxTotal,Integer minIdle, Integer maxIdle, Integer
      maxWaitMillis,Long minEvictableIdleTimeMillis ) {
      // 连接池的配置
      GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
      // 池中的最大连接数
      poolConfig.setMaxTotal(maxTotal);
      // 最少的空闲连接数
      poolConfig.setMinIdle(minIdle);
      // 最多的空闲连接数
      poolConfig.setMaxIdle(maxIdle);
      // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
      poolConfig.setMaxWaitMillis(maxWaitMillis);
      // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
      poolConfig.setLifo(true);
      // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
      poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
      // 连接耗尽时是否阻塞,默认为true
      poolConfig.setBlockWhenExhausted(true);
      // 连接池创建
      objectPool = new GenericObjectPool<>(new JudgeClientFactory(), poolConfig);
  }

  /**
   * 从连接池获取对象
   * @return 该对象
   */
  private JudgeClient borrowObject(){
    try {
      return objectPool.borrowObject();
    } catch (Exception e) {
      log.error("获取judgeClient对象出错",e);
    }
    //连接池失败则主动创建
    return createClient();
  }

  /**
   * 当连接池异常,则主动创建对象
   */
  private JudgeClient createClient(){
    return new JudgeClient(JudgeClientFactory.host, JudgeClientFactory.port);
  }

  /**
   * 判题任务执行器,具体任务逻辑在调用端
   * @param workCallBack 主要服务内容
   */
  public Runnable execute(WorkCallBack<Boolean,JudgeClient> workCallBack){
    return () -> {
      JudgeClient client = borrowObject();
      try {
        workCallBack.execute(client);
      } finally {
        /** 将连接对象返回给连接池 */
        objectPool.returnObject(client);
      }
    };
  }
}
