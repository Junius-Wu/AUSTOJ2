package cn.edu.aust.judge.client;

import org.junit.Before;
import org.junit.Test;

import cn.edu.aust.judger.proto.JudgeRequest;
import cn.edu.aust.judger.proto.JudgeResponse;
import cn.edu.aust.judger.proto.JudgeServerGrpc;
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
        channel = ManagedChannelBuilder.forAddress("192.168.99.100",50013)
            .usePlaintext(true).build();
        blockingStub = JudgeServerGrpc.newBlockingStub(channel).withCompression("gzip");
    }

    @Test
    public void judgeTest(){
        JudgeRequest request = JudgeRequest.newBuilder().setLanguage("java")
            .setMemoryLimit(65532)
            .setTimeLimit(2000)
            .setSolutionId(1)
            .setProblemId(1000)
            .setCodeSource(source)
            .build();
        JudgeResponse response = blockingStub.judge(request);
        System.out.println(response.getRuntimeResult());
    }


    private String source = "import java.io.*;\n" +
        "import java.util.*;\n" +
        "public class Main\n" +
        "{\n" +
        "            public static void main(String args[]) throws Exception\n" +
        "            {\n" +
        "                    Scanner cin=new Scanner(System.in);\n" +
        "                    int a=cin.nextInt(),b=cin.nextInt();\n" +
        "                    System.out.println(a+b);\n" +
        "            }\n" +
        "}";
}
