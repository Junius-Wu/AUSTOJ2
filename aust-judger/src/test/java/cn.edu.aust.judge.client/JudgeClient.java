package cn.edu.aust.judge.client;

import org.junit.Before;
import org.junit.Test;

import cn.edu.aust.judger.proto.JudgeServerGrpc;
import cn.edu.aust.judger.proto.judgeRequest;
import cn.edu.aust.judger.proto.judgeResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 测试连接服务端,服务端需要linux环境
 * @author Niu Li
 * @since 2017/2/20
 */
public class JudgeClient {

    private ManagedChannel channel; //一个gRPC信道
    private JudgeServerGrpc.JudgeServerBlockingStub blockingStub;//阻塞/同步 存根

    @Before
    public void init(){
        channel = ManagedChannelBuilder.forAddress("127.0.0.1",50013)
            .usePlaintext(true).build();
        blockingStub = JudgeServerGrpc.newBlockingStub(channel).withCompression("gzip");
    }

    @Test
    public void judgeTest(){
        judgeRequest request = judgeRequest.newBuilder().setLanguage("java")
            .setMemoryLimit(65532)
            .setTimeLimit(2000)
            .setSolutionId(1)
            .setProblemId(1001)
            .setCodeSource(source)
            .build();
        judgeResponse response = blockingStub.judge(request);
        System.out.println(response.getRuntimeResult());
    }


    private String source = "public class Main {\n" +
        "  public static void main(String[] args) {\n" +
        "    while (true){\n" +
        "      System.out.println(\"hahahahhaha\");\n" +
        "    }\n" +
        "  }\n" +
        "}";
}
