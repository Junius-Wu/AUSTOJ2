package cn.edu.aust.judger.server;

import cn.edu.aust.judger.service.JudgeService;
import cn.edu.aust.judger.util.Constant;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * RPC判题服务器
 * @author Niu Li
 * @since 2017/2/20
 */
@Slf4j
public class JudgeServer {

    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        final JudgeServer server = new JudgeServer();
        server.start();
        server.blockUntilShutdown();
    }

    /**
     * 启动服务
     */
    private void start() throws IOException {
        log.info("judge server start ...");
        server = ServerBuilder.forPort(Constant.judgePort)
                .addService(new JudgeService())
                .build()
                .start();
        log.info("judge server started ");

        //程序正常退出,系统调用 System.exit方法或虚拟机被关闭时执行该调用
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            this.stop();
            System.err.println("*** server shut down");
        }));
    }

    /**
     * 关闭服务器
     */
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * 阻塞主线程
     * @throws InterruptedException
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
