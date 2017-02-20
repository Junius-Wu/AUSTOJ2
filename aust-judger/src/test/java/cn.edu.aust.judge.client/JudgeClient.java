package cn.edu.aust.judge.client;

import cn.edu.aust.judger.proto.JudgeServerGrpc;
import cn.edu.aust.judger.proto.judgeRequest;
import cn.edu.aust.judger.proto.judgeResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Test;

/**
 * 测试连接服务端,服务端需要linux环境
 * @author Niu Li
 * @since 2017/2/20
 */
public class JudgeClient {

    private ManagedChannel channel; //一个gRPC信道
    private JudgeServerGrpc.JudgeServerBlockingStub blockingStub;//阻塞/同步 存根

    //初始化信道和存根
    public JudgeClient(String host,int port){
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true));
    }

    private JudgeClient(ManagedChannelBuilder<?> channelBuilder) {
        //信道应该是长连接,存根则每次调用创建一个
        channel = channelBuilder.build();
        //使用gzip压缩
        blockingStub = JudgeServerGrpc.newBlockingStub(channel).withCompression("gzip");
    }

    @Test
    public void judgeTest(){
        judgeRequest request = judgeRequest.newBuilder().setLanguage("java")
            .setMemoryLimit(65532)
            .setTimeLimit(2000)
            .setSolutionId(1)
            .setProblemId(1001)
            .setCodeSource("")
            .build();
        judgeResponse response = blockingStub.judge(request);
        System.out.println(response);
    }
}
