package cn.liangqinghai.study.netty.dubbo.impl;

import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.netty.dubbo.api.IPrinter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
@Slf4j
public class PrinterImpl implements IPrinter {
    @Override
    public String print(Object data) {
        log.info(JSONUtil.parse(data).toStringPretty());
        return JSONUtil.parse(data).toStringPretty();
    }
}
