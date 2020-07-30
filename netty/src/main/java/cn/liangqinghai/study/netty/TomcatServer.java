package cn.liangqinghai.study.netty;

import cn.hutool.json.JSONUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

import java.util.List;
import java.util.Map;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author Mr.Liang
 * @date 2020/3/15
 */
public class TomcatServer {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup parentGroup = new NioEventLoopGroup();

        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }));

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(parentGroup, childGroup)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpRequestDecoder())
                                .addLast(new HttpResponseEncoder())
                                .addLast(new TomcatServerHandler());
                    }
                })
                .bind(8888)
                .sync()
                .channel()
                .closeFuture()
                .sync();

    }


    /**
     * httpRequest
     */
    public static class CustomHttpRequest {

        private HttpRequest request;
        private ChannelHandlerContext context;

        public CustomHttpRequest(HttpRequest request, ChannelHandlerContext context) {
            this.request = request;
            this.context = context;
        }

        public CustomHttpRequest() {
        }

        public HttpRequest getRequest() {
            return request;
        }

        public CustomHttpRequest setRequest(HttpRequest request) {
            this.request = request;
            return this;
        }

        public ChannelHandlerContext getContext() {
            return context;
        }

        public CustomHttpRequest setContext(ChannelHandlerContext context) {
            this.context = context;
            return this;
        }

        public String getUri() {
            return request.uri();
        }

        public String getMethod() {
            return request.method().name();
        }

        public Map<String, List<String>> getParams() {
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            return decoder.parameters();
        }

    }

    /**
     * 自定义HttpResponse
     */
    public static class CustomHttpResponse {

        private HttpRequest request;

        private ChannelHandlerContext context;

        public CustomHttpResponse(HttpRequest request, ChannelHandlerContext context) {
            this.request = request;
            this.context = context;
        }

        public CustomHttpResponse() {
        }

        public HttpRequest getRequest() {
            return request;
        }

        public CustomHttpResponse setRequest(HttpRequest request) {
            this.request = request;
            return this;
        }

        public ChannelHandlerContext getContext() {
            return context;
        }

        public CustomHttpResponse setContext(ChannelHandlerContext context) {
            this.context = context;
            return this;
        }

        /**
         * 写数据
         *
         * @param content
         * @throws Exception
         */
        public void write(byte[] content) throws Exception {
            ByteBuf byteBuf = Unpooled.copiedBuffer(content);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            HttpHeaders headers = response.headers();
            headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            headers.set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            headers.set(HttpHeaderNames.EXPIRES, 0);

            this.context.writeAndFlush(response);
        }

    }

    /**
     * servlet 抽象
     */
    public static abstract class BaseCustomServlet {

        /**
         * handle get request
         *
         * @param request
         * @param response
         * @throws Exception
         */
        public abstract void doGet(CustomHttpRequest request, CustomHttpResponse response) throws Exception;

        /**
         * handle post request
         *
         * @param request
         * @param response
         * @throws Exception
         */
        public abstract void doPost(CustomHttpRequest request, CustomHttpResponse response) throws Exception;

    }

    /**
     * 自定义Servlet
     */
    public static class CustomServlet extends BaseCustomServlet {
        @Override
        public void doGet(CustomHttpRequest request, CustomHttpResponse response) throws Exception {

            String format = String.format("Uri: %s, Method: %s, params: %s",
                    request.getUri(), request.getMethod(), JSONUtil.parse(request.getParams()).toStringPretty());
            System.out.println(format);

            response.write(format.getBytes(UTF_8));

        }

        @Override
        public void doPost(CustomHttpRequest request, CustomHttpResponse response) throws Exception {
            doGet(request, response);
        }
    }

    /**
     * 请求处理器
     */
    public static class TomcatServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            if (msg instanceof HttpRequest) {

                CustomServlet customServlet = new CustomServlet();
                HttpRequest request = (HttpRequest) msg;
                CustomHttpRequest customHttpRequest = new CustomHttpRequest(request, ctx);
                CustomHttpResponse customHttpResponse = new CustomHttpResponse(request, ctx);

                customServlet.doGet(customHttpRequest, customHttpResponse);

            }

        }
    }

}
