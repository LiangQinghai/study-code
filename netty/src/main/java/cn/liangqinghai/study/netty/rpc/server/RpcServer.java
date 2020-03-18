package cn.liangqinghai.study.netty.rpc.server;

import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.netty.rpc.bean.InvokeMessage;
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

import java.io.File;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Liang
 * @date 2020/3/18
 */
public class RpcServer {

    private static RpcServer rpcServer;

    private RpcServer() {
    }

    public static synchronized RpcServer getInstance() {
        if (rpcServer == null) {
            rpcServer = new RpcServer();
        }
        return rpcServer;
    }

    /**
     * 实例对象
     */
    private Map<String, Object> classHashMap = new HashMap<>();

    /**
     * 全类名缓存
     */
    private List<String> classDeclareName = new ArrayList<>();

    /**
     * 入口
     *
     * @param packageName
     */
    public void publish(String packageName) {

        scanPackage(packageName);

        register();

        try {
            runServe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动服务
     */
    private void runServe() throws InterruptedException {

        NioEventLoopGroup parent = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            parent.shutdownGracefully();
            child.shutdownGracefully();
        }));

        System.out.println(JSONUtil.parse(this.classHashMap));
        new ServerBootstrap().group(parent, child)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)))
                                .addLast(new ChannelInboundHandlerAdapter() {

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("receive......");
                                        if (msg instanceof InvokeMessage) {
                                            InvokeMessage message = (InvokeMessage) msg;

                                            Object invoke = classHashMap.get(message.getClassName());
                                            Object o = invoke
                                                    .getClass()
                                                    .getMethod(message.getMethodName(), message.getParameterTypes())
                                                    .invoke(invoke, message.getParameterValues());

                                            ctx.writeAndFlush(o);
                                        }

                                    }

                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        ctx.close();
                                    }
                                });
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1", 8888))
                .sync()
                .channel()
                .closeFuture()
                .sync();
    }

    /**
     * 创建实例
     */
    private void register() {

        this.classDeclareName.forEach(name -> {
            try {
                Class<?> clazz = Class.forName(name);
                Class<?> anInterface = clazz.getInterfaces()[0];
                this.classHashMap.put(anInterface.getCanonicalName(), clazz.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 扫描包
     *
     * @param packageName
     */
    private void scanPackage(String packageName) {

        URL resource = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));

        assert resource != null;
        File file = new File(resource.getPath());

        File[] files = file.listFiles();

        assert files != null;
        for (File f : files) {
            if (f.isDirectory()) {
                scanPackage(packageName + "." + f.getName());
            } else {
                if (f.getName().endsWith(".class")) {
                    classDeclareName.add(packageName + "." + f.getName().replace(".class", ""));
                }
            }
        }

    }


    public static void main(String[] args) {

        RpcServer instance = getInstance();

        instance.publish("cn.liangqinghai.study.netty.rpc.impl");

    }

}
