package cn.liangqinghai.study.netty.utils;

import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Liang
 * @date 2020/3/21
 */
public class UtilsTest {

    @Test
    public void testCreatePath() throws Exception {
        ZkUtil.createPath("cn.liangqinghai.study.netty.utils.UtilsTest", "localhost:8888");
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void testGetList() throws Exception {

        Map<String, List<ZkUtil.ServiceIpPort>> serviceList = ZkUtil.getServiceList();

        System.out.println(JSONUtil.parse(serviceList).toStringPretty());

    }

}
