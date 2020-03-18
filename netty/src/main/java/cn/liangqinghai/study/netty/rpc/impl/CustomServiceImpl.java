package cn.liangqinghai.study.netty.rpc.impl;

import cn.liangqinghai.study.netty.rpc.api.ICustomService;

/**
 * @author Mr.Liang
 * @date 2020/3/18
 */
public class CustomServiceImpl implements ICustomService {
    @Override
    public String getName(String name) {

        System.out.println("ReceiveName: " + name);

        return "Handle_prefix: " + name;
    }
}
