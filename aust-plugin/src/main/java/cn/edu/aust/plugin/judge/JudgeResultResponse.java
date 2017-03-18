package cn.edu.aust.plugin.judge;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 封装RPC调用返回的判题结果
 * @author Niu Li
 * @since 2017/3/15
 */
@Data
@NoArgsConstructor
@ToString
public class JudgeResultResponse {

  /**
   * 错误码
   */
  private Integer exitCode;
  /**
   * 错误消息,暂时未使用到
   */
  private String runtimeResult;
  /**
   * 使用时间
   */
  private Integer useTime;
  /**
   * 使用内存
   */
  private Integer useMemory;
  /**
   * 标识判题请求
   */
  private Boolean isSuccess;
  /**
   * 通过测试案例数量
   */
  private Integer testcase;

}
