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
  public String host;
  /**
   * 判题服务器端口
   */
  public int port;

  public JudgeClientFactory(String host, int port) {
    this.host = host;
    this.port = port;
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
