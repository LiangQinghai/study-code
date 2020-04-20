package cn.liangqinghai.study.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @Title HelloWorldClient
 * @ProjectName study-code
 * @Description
 * @date 2020/4/20 20:24
 */
public class HelloWorldClient {

    private ManagedChannel managedChannel;

    private GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host, int port) {
        managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = GreeterGrpc.newBlockingStub(managedChannel);
    }

    public HelloWorldClient shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        return this;
    }

    public HelloWorldClient greet(String name) {

        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply reply = blockingStub.sayHello(request);

        System.out.println(reply.getMessage());

        return this;
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50052);

        for (int i = 0; i < 100; i++) {
            client.greet("No." + (i + 1));
            TimeUnit.SECONDS.sleep(2);
        }

        client.shutdown();
    }

}
