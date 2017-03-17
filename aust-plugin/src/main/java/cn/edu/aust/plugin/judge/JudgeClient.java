package cn.edu.aust.plugin.judge;

import com.google.common.base.Preconditions;

import java.util.concurrent.TimeUnit;

import cn.edu.aust.judger.proto.JudgeRequest;
import cn.edu.aust.judger.proto.JudgeResponse;
import cn.edu.aust.judger.proto.JudgeServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * 判题客户端
 *
 * @author Niu Li
 * @since 2017/3/5
 */
@Slf4j
public class JudgeClient {
  /**
   * 通信信道
   */
  private final ManagedChannel channel;
  /**
   * 阻塞同步存根
   */
  private JudgeServerGrpc.JudgeServerBlockingStub blockingStub;


  public JudgeClient(String host, Integer port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
                                   .usePlaintext(true)
                                   .build();
  }

  /**
   * 关闭方法
   */
  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /**
   * 判题方法
   *
   * @param solutionId  对应数据库solution表记录,主要用于日志快速查找
   * @param problemId   对应题目编号
   * @param sourceCode  源码
   * @param language    原因
   * @param timeLimit   时间限制
   * @param memoryLimit 内存限制
   * @return 判题结果
   */
  public JudgeResultResponse judge(Long solutionId, Long problemId, String sourceCode, String language,
                   Integer timeLimit, Integer memoryLimit) {
    JudgeRequest.Builder builder = JudgeRequest.newBuilder();
    JudgeRequest judgeRequest = builder.setSolutionId(solutionId.intValue())
                                       .setProblemId(problemId.intValue())
                                       .setCodeSource(sourceCode)
                                       .setLanguage(language)
                                       .setTimeLimit(timeLimit)
                                       .setMemoryLimit(memoryLimit)
                                       .build();
    JudgeResultResponse judgeResultResponse = new JudgeResultResponse();
    try {
      blockingStub = JudgeServerGrpc.newBlockingStub(channel);
      JudgeResponse judgeResponse = blockingStub.judge(judgeRequest);
      Preconditions.checkNotNull(judgeResponse,"判题异常");

      judgeResultResponse.setExitCode(judgeResponse.getExitCode());
      judgeResultResponse.setRuntimeResult(judgeResponse.getRuntimeResult());
      judgeResultResponse.setUseMemory(judgeResponse.getUsedMemory());
      judgeResultResponse.setUseTime(judgeResponse.getUsedTime());
      judgeResultResponse.setIsSuccess(true);
    } catch (Exception e) {
      log.error("JudgeClient judge error",e);
      judgeResultResponse.setExitCode(99);
      judgeResultResponse.setRuntimeResult("判题异常");
      judgeResultResponse.setIsSuccess(false);
      return judgeResultResponse;
    }
    return judgeResultResponse;
  }
}
