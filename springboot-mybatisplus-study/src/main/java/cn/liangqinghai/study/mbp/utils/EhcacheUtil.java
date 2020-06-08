package cn.liangqinghai.study.mbp.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LiangQinghai
 * @Title EhcacheUtil
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 16:09
 */
@Component
public class EhcacheUtil {

    public final static String ADMIN_EHCACHE = "cache";

    public final static String MENU_EHCACHE = "menu_";

    @Autowired
    public CacheManager manager;

    public Object get(String cacheName, Object key) {

        Cache cache = manager.getCache(cacheName);

        if (cache != null) {

            Element element = cache.get(key);

            return element == null ? null : element.getObjectValue();

        }

        return null;

    }

    public void put(String cacheName, Object key, Object value) {

        Cache cache = manager.getCache(cacheName);

        if (cache != null) {
            cache.put(new Element(key, value));
        }

    }

    public boolean remove(String cacheName, Object key) {

        Cache cache = manager.getCache(cacheName);

        if (cache != null) {
            return cache.remove(key);
        }

        return false;

    }

}
