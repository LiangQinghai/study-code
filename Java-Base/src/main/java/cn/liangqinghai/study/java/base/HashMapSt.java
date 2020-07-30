package cn.liangqinghai.study.java.base;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LiangQinghai
 * @Title HashMapSt
 * @ProjectName study-code
 * @Description
 * @date 2020/3/2 19:56
 */
public class HashMapSt {

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(1);
        List<Integer> integers = Stream.generate(i::getAndIncrement).limit(20).collect(Collectors.toList());

        integers.stream().limit(80).forEach(System.out::println);

    }

}
