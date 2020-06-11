package cn.liangqinghai.study.log.client;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title AbClient
 * @ProjectName study-code
 * @Description
 * @date 2020/6/9 10:51
 */
public abstract class BaseClient {

    private static BaseClient client;

    public void pushMessage(String key, String strings) {}

    public void pushMessageList(String key, List<String> list) {}

    public static BaseClient getClient() {
        return client;
    }

    public static void setClient(BaseClient client) {
        BaseClient.client = client;
    }
}
