package cn.liangqinghai.study.netty.rpc.client;

import cn.liangqinghai.study.netty.rpc.api.ICustomService;
import cn.liangqinghai.study.netty.rpc.bean.Constants;
import cn.liangqinghai.study.netty.rpc.bean.InvokeMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * @author Mr.Liang
 * @date 2020/3/18
 */
public class RpcClient {

    @SuppressWarnings("unchecked")
    private static <T> T getInstance(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) -> rpcInvoke(clazz, proxy, method, args));

    }

    private static Object rpcInvoke(Class<?> clazz, Object proxy, Method method, Object[] args) throws InterruptedException {

        NioEventLoopGroup gr = new NioEventLoopGroup();
        ClientHandler clientHandler = new ClientHandler();

        new Bootstrap().group(gr)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(1024 * 64, ClassResolvers.cacheDisabled(null)))
                                .addLast(clientHandler);
                    }
                })
                .connect(new InetSocketAddress(Constants.IP, Constants.PORT))
                .sync()
                .channel()
                .writeAndFlush(new InvokeMessage().setClassName(clazz.getCanonicalName())
                        .setMethodName(method.getName())
                        .setParameterTypes(method.getParameterTypes())
                        .setParameterValues(args))
                .sync()
                .channel()
                .closeFuture()
                .sync();

        gr.shutdownGracefully().sync();

        return clientHandler.getResult();
    }

    /**
     * 客户端处理器
     *
     */
    private static class ClientHandler extends SimpleChannelInboundHandler<Object> {

        private Object result;

        public Object getResult() {
            return result;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

            result = msg;

            // 信息读完需要关闭上下文，否则造成阻塞无法往下执行代码
            ctx.close().sync();

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) {

        ICustomService service = getInstance(ICustomService.class);

        System.out.println(service.getName("HelloWorld"));

    }

}
