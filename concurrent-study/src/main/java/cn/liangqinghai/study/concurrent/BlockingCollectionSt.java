package cn.liangqinghai.study.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @Title BlockingCollectionSt
 * @ProjectName study-code
 * @Description
 * @date 2020/3/4 14:44
 */
public class BlockingCollectionSt {

    public static void main(String[] args) throws Exception{
        blockingArrayQueue();
    }

    private static void blockingArrayQueue() throws InterruptedException {

        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<>(3);

        for (int i = 0; i < 20; i++) {
            integers.put(i);
        }

        System.out.println(integers.size());
    }

}
