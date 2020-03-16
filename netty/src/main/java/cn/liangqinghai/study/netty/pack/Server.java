package cn.liangqinghai.study.netty.pack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author Mr.Liang
 * @date 2020/3/16
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup parentGroup = new NioEventLoopGroup();

        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                childGroup.shutdownGracefully().sync();
                parentGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));


        new ServerBootstrap()
                .group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline
//                                .addLast(new LineBasedFrameDecoder(5120))
//                                .addLast(new DelimiterBasedFrameDecoder(5120, Unpooled.copiedBuffer(" ".getBytes())))
//                                .addLast(new FixedLengthFrameDecoder(1))
                                .addLast(new LengthFieldPrepender(4))
                                .addLast(new LengthFieldBasedFrameDecoder(
                                        1024, //指定了每个包所能传递的最大数据包大小
                                        0, //指定了长度字段在字节码中的偏移量
                                        4, //指定了长度字段所占用的字节长度
                                        0, //对一些不仅包含有消息头和消息体的数据进行消息头的长度的调整，这样就可以只得到消息体的数据，这里的lengthAdjustment指定的就是消息头的长度
                                        4 //对于长度字段在消息头中间的情况，可以通过initialBytesToStrip忽略掉消息头以及长度字段占用的字节。
                                ))
                                .addLast(new StringDecoder())
                                .addLast(new CustomHandler());
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1", 8888))
                .sync()
                .channel()
                .closeFuture()
                .sync();

    }

    private static final class CustomHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            System.out.println("Message: " + msg);

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

}
