package cn.edu.aust.plugin.judge;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 判题客户端连接池
 * @author Niu Li
 * @since 2017/3/5
 */
public class JudgeClientFactory extends BasePooledObjectFactory<JudgeClient> {

  /**
   * 判题服务器地址
   */
  public static String host;
  /**
   * 判题服务器端口
   */
  public static int port;

  /**
   * 配置地址和端口
   * @param _host 地址
   * @param _port 端口
   */
  public static void config(String _host,Integer _port){
    host = _host;
    port = _port;
  }

  @Override
  public JudgeClient create() throws Exception {
    return new JudgeClient(host,port);
  }

  @Override
  public PooledObject<JudgeClient> wrap(JudgeClient judgeClient) {
    return new DefaultPooledObject<>(judgeClient);
  }

  @Override
  public void destroyObject(PooledObject<JudgeClient> p) throws Exception {
    p.getObject().shutdown();
    super.destroyObject(p);
  }


}
