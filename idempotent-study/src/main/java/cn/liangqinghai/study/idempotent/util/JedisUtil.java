package cn.liangqinghai.study.idempotent.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author LiangQinghai
 * @Title JedisUtil
 * @ProjectName study-code
 * @Description
 * @date 3/26/2020 10:19 AM
 */
@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取jedis
     *
     * @return
     */
    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {

        Jedis jedis = null;
        try {

            jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key, 2 * 60);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (jedis != null) {
                jedis.close();
            }

        }

    }

    /**
     * 获取键值
     *
     * @param key
     * @return
     */
    public String get(String key) {

        if (StringUtils.isEmpty(key)) {
            return null;
        }

        Jedis jedis = null;

        try {

            jedis = getJedis();

            return jedis.get(key);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return null;
    }

}
