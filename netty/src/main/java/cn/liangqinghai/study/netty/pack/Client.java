package cn.liangqinghai.study.netty.pack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Liang
 * @date 2020/3/16
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Runtime.getRuntime().addShutdownHook(new Thread(eventLoopGroup::shutdownGracefully));

        new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1", 8888))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringEncoder())
                                .addLast(new CustomClientHandler());
                    }
                })
                .connect()
                .sync()
                .channel()
                .closeFuture()
                .sync();

    }

    private static final class CustomClientHandler extends SimpleChannelInboundHandler<String> {

        static String message = "Hello World";

        static StringBuilder sb = new StringBuilder();

        static {
            for (int i = 0; i < 10000; i++) {
                sb.append(message);
                if (i % 100 == 0) {
                    sb.append("\n");
                } else {
                    sb.append(" ");
                }
            }
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println("------");
            for (int i = 0; i < 5; i++) {
                ctx.writeAndFlush("Hello World");
                System.out.println(i);
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

                ctx.writeAndFlush(sb.toString());

//            for (int i = 0; i < 100; i++) {
//                ctx.writeAndFlush("Hello World");
//            }

                TimeUnit.MILLISECONDS.sleep(200);

        }

    }

}
