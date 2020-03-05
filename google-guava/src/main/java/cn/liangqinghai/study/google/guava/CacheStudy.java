package cn.liangqinghai.study.google.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @Title CacheStudy
 * @ProjectName study-code
 * @Description
 * @date 2020/2/29 14:52
 */
public class CacheStudy {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors(),
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(20, TimeUnit.SECONDS)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .maximumSize(5)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .removalListener((RemovalListener<String, String>) listener -> {
                        String key = listener.getKey();
                        System.out.println("Invalide" + key + "---" + listener.getValue());
                        RemovalCause cause = listener.getCause();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (key.equals("15")) {
                            throw new RuntimeException("asd");
                        }
                })
                .weakKeys()
                .build();


        int i = 1;

        for (;;) {

            cache.put(String.valueOf(i), "value_" + i);
            System.out.println("add: " + i);

            i ++;

            if (i % 15 == 0) {
                cache.invalidateAll();
            }

            Thread.sleep(100);

        }

//        executor.shutdown();
    }


}
