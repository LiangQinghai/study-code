package cn.liangqinghai.study.netty;

import cn.hutool.json.JSONUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author Mr.Liang
 * @date 2020/3/14
 */
public class HttpServer {

    public static void main(String[] args) throws InterruptedException {

        // 主eventLoop， 处理用户连接
        EventLoopGroup parentLoop = new NioEventLoopGroup();

        // 子eventLoop，处理channel数据
        EventLoopGroup childLoop = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentLoop, childLoop)
                // 使用nio channel处理
                .channel(NioServerSocketChannel.class)
                // 添加handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //
                        ChannelPipeline pipeline = ch.pipeline();

                        // 添加http请求handler
                        pipeline.addLast(new HttpServerCodec());

                        // 自定义handler
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                if (msg instanceof HttpRequest) {

                                    HttpRequest httpRequest = (HttpRequest) msg;

                                    System.out.println(JSONUtil.parse(httpRequest).toStringPretty());

                                    ByteBuf content = Unpooled.copiedBuffer("HelloWorld", UTF_8);
                                    DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

                                    HttpHeaders headers = response.headers();
                                    headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                                    headers.set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

                                    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

                                }
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                ctx.close();
                            }
                        });
                    }
                });

        // 同步方式绑定端口号
        ChannelFuture future = serverBootstrap.bind(8888).sync();

        // 关闭channelFuture
        future.channel().closeFuture().sync();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            childLoop.shutdownGracefully();
            parentLoop.shutdownGracefully();
        }));
    }

}
