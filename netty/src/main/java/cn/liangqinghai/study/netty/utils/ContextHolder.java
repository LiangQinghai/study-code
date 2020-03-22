package cn.liangqinghai.study.netty.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Mr.Liang
 * @date 2020/3/21
 */
public class ContextHolder {

    private static Properties config;

    static {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load() throws IOException {
        InputStream resourceAsStream = ContextHolder.class.getClassLoader().getResourceAsStream("config.properties");
        config = new Properties();
        config.load(resourceAsStream);
    }

    public static Properties getConfig() {
        return config;
    }


    /**
     * 获取运行端口
     *
     * @return
     */
    public static Integer getServerPort() {

        String port = config.getProperty("server.port", "8899");

        return Integer.parseInt(port);

    }

}
