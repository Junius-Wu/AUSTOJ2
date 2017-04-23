package cn.edu.aust.plugin.storage;

import java.io.InputStream;

/**
 * 抽象存储接口
 * @author Niu Li
 * @since 2017/4/23
 */
public interface StoragePlugin {
  /**
   * 文件上传
   *
   * @param fileName
   *            上传路径
   * @param inFile
   *            上传文件
   */
  void upload(String fileName, InputStream inFile);

  /**
   * 获取访问URL
   *
   * @param path
   *            上传路径
   * @return 访问URL
   */
  String getUrl(String path);
}
