package cn.edu.aust.plugin.storage.localStrage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import cn.edu.aust.plugin.storage.StoragePlugin;
import lombok.extern.slf4j.Slf4j;

/**
 * 本地存储插件
 * @author Niu Li
 * @since 2017/4/23
 */
@Slf4j
public class LocalStoragePlugin implements StoragePlugin{
  /**
   * 注入属性,文件绝对路径
   */
  private String absolutePathPrefix;

  @Override
  public void upload(String fileName, InputStream inFile) {
    if (StringUtils.isEmpty(absolutePathPrefix)) {
      throw new IllegalArgumentException("no absolutePathPrefix config");
    }
    try {
      File destFile = new File(absolutePathPrefix+fileName);
      if (destFile.exists() && destFile.delete()) {

      }
      FileUtils.copyInputStreamToFile(inFile,destFile);
    } catch (IOException e) {
      log.error("upload fail",e);
    }
  }

  @Override
  public String getUrl(String fileName) {
    if (StringUtils.isEmpty(absolutePathPrefix)) {
      throw new IllegalArgumentException("no absolutePathPrefix config");
    }
    return absolutePathPrefix+fileName;
  }
//  ---------------------------注入需要
  public void setAbsolutePathPrefix(String absolutePathPrefix) {
    this.absolutePathPrefix = absolutePathPrefix;
  }

  public String getAbsolutePathPrefix() {
    return absolutePathPrefix;
  }
}
