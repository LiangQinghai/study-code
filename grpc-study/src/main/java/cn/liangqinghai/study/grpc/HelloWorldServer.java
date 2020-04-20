package cn.liangqinghai.study.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 * @author LiangQinghai
 * @Title HelloWorld
 * @ProjectName study-code
 * @Description
 * @date 2020/4/20 19:32
 */
public class HelloWorldServer {

    private Server server;

    private HelloWorldServer start() throws IOException {
        server = ServerBuilder.forPort(50052)
                .addService(new GreeterImpl())
                .build().start();

        System.out.println("Server start...");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                HelloWorldServer.this.stop();
            }
        });
        return this;
    }

    private HelloWorldServer stop() {
        server.shutdown();
        return this;
    }

    private HelloWorldServer blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
        return this;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new HelloWorldServer().start().blockUntilShutdown();
    }

    private static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            System.out.println("Service: " +  request.getName());
            HelloReply helloReply = HelloReply.newBuilder().setMessage("Hello: " + request.getName()).build();
            responseObserver.onNext(helloReply);
            responseObserver.onCompleted();
        }
    }
}
