package cn.liangqinghai.study.netty.rpc.client;

import cn.liangqinghai.study.netty.rpc.api.ICustomService;
import cn.liangqinghai.study.netty.rpc.bean.InvokeMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
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

        final Object[] result = {null};

        NioEventLoopGroup gr = new NioEventLoopGroup();

        new Bootstrap().group(gr)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)))
                                .addLast(new SimpleChannelInboundHandler<Object>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        result[0] = msg;
                                    }
                                });
                    }
                })
                .connect(new InetSocketAddress("127.0.0.1", 8888))
                .sync()
                .channel()
                .writeAndFlush(new InvokeMessage().setClassName(clazz.getCanonicalName())
                        .setMethodName(method.getName())
                        .setParameterTypes(method.getParameterTypes())
                        .setParameterValues(args))
                .channel()
                .closeFuture()
                .sync();

        return result[0];
    }


    public static void main(String[] args) {

        ICustomService service = getInstance(ICustomService.class);

        service.getName("HelloWorld");

    }

}
