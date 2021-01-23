package cn.liangqinghai.study.netty.utils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author Mr.Liang
 * @date 2020/3/21
 */
@Slf4j
public final class ZkUtil {

    private static volatile CuratorFramework client;

    public static CuratorFramework getClient() {
        if (client != null) {
            return client;
        }

//        client = CuratorFrameworkFactory.newClient(ContextHolder.getConfig().getProperty("zookeeper.cluster"),
//                5000,
//                5000,
//                new ExponentialBackoffRetry(1000, 3));

        client = CuratorFrameworkFactory.builder()
                .connectString(ContextHolder.getConfig().getProperty("zookeeper.cluster"))
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .namespace("handleRpc")
                .build();
        client.start();
        Runtime.getRuntime().addShutdownHook(new Thread(client::close));
        return client;
    }

    /**
     * 创建父目录
     *
     * @param serviceName
     * @param serviceAddress
     * @throws Exception
     */
    public static void createPath(String serviceName, String serviceAddress) throws Exception {
        getClient();
        if (client == null || client.getState().equals(CuratorFrameworkState.STOPPED) ||
        client.getState().equals(CuratorFrameworkState.LATENT)) {
            throw new Exception("Client is null");
        }
        String homePath = ContextHolder.getConfig().getProperty("zookeeper.home") + "/" + serviceName;
        if (client.checkExists().forPath(homePath) == null) {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(homePath, "0".getBytes());
        }

        client.create()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(homePath + "/" + serviceAddress, "0".getBytes());

    }

    /**
     * 获取节点数据
     *
     * @return
     * @throws Exception
     */
    public static Map<String, List<ServiceIpPort>> getServiceList() throws Exception {

        getClient();

        Map<String, List<ServiceIpPort>> serviceMap = new HashMap<>();

        String home = ContextHolder.getConfig().getProperty("zookeeper.home");
        List<String> serviceList = client.getChildren()
                .forPath(home);

        for (String s : serviceList) {

            List<String> ipPorts = client.getChildren()
                    .forPath(home + "/" + s);

            if (CollectionUtil.isEmpty(ipPorts)) {
                serviceMap.put(s, Collections.emptyList());
            } else {

                List<ServiceIpPort> ipPortList = new ArrayList<>();

                for (String ipPort : ipPorts) {
                    String[] split = ipPort.split(":");
                    if (split.length == 2) {
                        ipPortList.add(new ServiceIpPort().setIp(split[0]).setPort(Integer.parseInt(split[1])));
                    }
                }

                serviceMap.put(s, ipPortList);
            }
        }

        return serviceMap;

    }

    /**
     * 服务地址ip:port
     */
    @Data
    @Accessors(chain = true)
    public static class ServiceIpPort {

        private String ip;

        private Integer port;

    }

}
