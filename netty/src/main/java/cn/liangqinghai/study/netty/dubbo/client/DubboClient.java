package cn.liangqinghai.study.netty.dubbo.client;

import cn.liangqinghai.study.netty.rpc.bean.InvokeMessage;
import cn.liangqinghai.study.netty.utils.ZkUtil;
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
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
public class DubboClient {

    private static volatile DubboClient instance;

    private DubboClient() throws Exception {
        instanceInvokeMap = ZkUtil.getServiceList();
    }

    private volatile Map<String, List<ZkUtil.ServiceIpPort>> instanceInvokeMap;

    public static DubboClient getInstance() throws Exception {

        if (instance == null) {
            instance = new DubboClient();
        }

        return instance;

    }

    /**
     * 对外入口
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getInstanceByClass(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                ((proxy, method, args) -> invoke(clazz, method, args)));
    }

    /**
     * rpc调用
     *
     * @param clazz
     * @param method
     * @param args
     * @return
     * @throws InterruptedException
     */
    private Object invoke(Class<?> clazz, Method method, Object[] args) throws InterruptedException {
        InvokeMessage message = new InvokeMessage();
        message.setClassName(clazz.getCanonicalName())
                .setMethodName(method.getName())
                .setParameterTypes(method.getParameterTypes())
                .setParameterValues(args);

        ClientHandler clientHandler = new ClientHandler();

        NioEventLoopGroup group = new NioEventLoopGroup();

        new Bootstrap().group(group)
//                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                .addLast(clientHandler);
                    }
                })
                .connect(instanceInvokeMap.get(clazz.getCanonicalName()).get(0).getIp(), instanceInvokeMap.get(clazz.getCanonicalName()).get(0).getPort())
                .sync()
                .channel()
                .writeAndFlush(message)
                .sync()
                .channel()
                .closeFuture()
                .sync();

        group.shutdownGracefully().sync();

        return clientHandler.getResult();

    }

    /**
     * client handle
     */
    private static class ClientHandler extends SimpleChannelInboundHandler<Object> {

        private Object result;

        public Object getResult() {
            return result;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            result = msg;
            ctx.close().sync();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

}
