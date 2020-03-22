package cn.liangqinghai.study.netty.dubbo.server;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
public class ServerRunner {

    public static void main(String[] args) throws InterruptedException {

        DubboServer.getInstance().run("cn.liangqinghai.study.netty.dubbo.impl");

    }

}
