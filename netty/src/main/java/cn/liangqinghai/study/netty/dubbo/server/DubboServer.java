package cn.liangqinghai.study.netty.dubbo.server;

import cn.hutool.core.net.NetUtil;
import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.netty.rpc.bean.InvokeMessage;
import cn.liangqinghai.study.netty.utils.ContextHolder;
import cn.liangqinghai.study.netty.utils.PackageScanner;
import cn.liangqinghai.study.netty.utils.ZkUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
public class DubboServer {

    private static volatile DubboServer instance;

    private DubboServer() {}

    public static DubboServer getInstance() {

        if (instance == null) {
            instance = new DubboServer();
        }
        return instance;
    }

    private volatile Map<String, Object> instanceMap = new HashMap<>();

    /**
     * server 对外入口
     *
     * @param packagePath
     * @throws InterruptedException
     */
    public void run(String packagePath) throws InterruptedException {

        // scan package
        List<String> scan = PackageScanner.scan(packagePath);

        // do register
        doRegister(scan);

        // run
        runServer();

    }

    /**
     * 启动服务绑定ip:port
     *
     * @throws InterruptedException
     */
    private void runServer() throws InterruptedException {

        NioEventLoopGroup parent = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();

        new ServerBootstrap().group(parent, child)
                .option(ChannelOption.SO_BACKLOG, 2014)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                .addLast(new ServerChannelHandler(instanceMap));
                    }
                })
                .bind(NetUtil.getLocalhostStr(), ContextHolder.getServerPort())
                .sync()
                .channel()
                .closeFuture()
                .sync();
    }

    /**
     * 本地map添加接口实例，zk添加接口地址
     *
     * @param scan
     */
    private void doRegister(List<String> scan) {

        scan.forEach(pack -> {

            try {
                Class<?> forName = Class.forName(pack);
                Object newInstance = forName.newInstance();
                String hostAddress = NetUtil.getLocalhost().getHostAddress();
                String serverAddress = hostAddress + ":" + ContextHolder.getServerPort();
                ZkUtil.createPath(forName.getInterfaces()[0].getName(), serverAddress);
                this.instanceMap.put(forName.getInterfaces()[0].getName(), newInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }


    /**
     * server channel
     */
    @Slf4j
    private static class ServerChannelHandler extends ChannelInboundHandlerAdapter {

        private Map<String, Object> instanceMap;

        public ServerChannelHandler(Map<String, Object> instanceMap) {
            this.instanceMap = instanceMap;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            if (msg instanceof InvokeMessage) {

                InvokeMessage message = (InvokeMessage) msg;

                Object o = instanceMap.get(message.getClassName());

                ctx.writeAndFlush(o.getClass()
                        .getMethod(message.getMethodName(), message.getParameterTypes())
                        .invoke(o, message.getParameterValues()));

                log.info("Write data completely.");

            }

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

}
