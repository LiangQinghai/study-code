package cn.liangqinghai.study.netty.dubbo.client;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.netty.dubbo.api.IPrinter;
import cn.liangqinghai.study.netty.dubbo.bean.Task;

import java.util.Date;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
public class ClientRunner {

    public static void main(String[] args) throws Exception {

        DubboClient instance = DubboClient.getInstance();

        IPrinter printer = instance.getInstanceByClass(IPrinter.class);

        String helloWorld = printer.print(JSONUtil.parse(Task.builder().id(IdUtil.simpleUUID())
                .cron("* */5 * * ?").status(Task.Status.ENABLE).createDate(new Date())
                .name("HelloWorld").build()).toStringPretty());

        System.out.println(helloWorld);

    }

}
