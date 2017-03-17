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

  private Integer exitCode;
  private String runtimeResult;
  private Integer useTime;
  private Integer useMemory;
  private Boolean isSuccess;

}
