package cn.liangqinghai.study.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title Test01
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 11:20 AM
 */
public class Test01 {

    private static List<String> words = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            words.add("World_No." + i);
        }
    }

    @Test
    public void one() {

        Flux<String> just = Flux.just("Hello", "world");
        Flux<String> fromIterable = Flux.fromIterable(words);

        just.subscribe(System.out::println);

        System.out.println("-------------");

        fromIterable.subscribe(System.out::println);

    }

    @Test
    public void two() {

        Flux.fromIterable(words)
                .flatMap(s -> Flux.fromArray(s.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (s, c) -> String.format("%2d, %s", c, s))
                .subscribe(System.out::println);

    }

    @Test
    public void three() {

        Mono<String> missingTest = Mono.just("m");

        Flux.fromIterable(words)
                .flatMap(s -> Flux.fromArray(s.split("")))
                .concatWith(missingTest)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (s, c) -> String.format("%2d, %s", c, s))
                .subscribe(System.out::println);

    }

    @Test
    public void four() {

        Mono.just("Hello")
                .concatWith(Mono.just("World"))
                .delaySubscription(Duration.ofSeconds(5))
                .toStream()// 生成阻塞实例
                .forEach(System.out::println);

    }

    @Test
    public void five() {

        Mono<String> late = Mono.just("late").delaySubscription(Duration.ofSeconds(3));

        Flux<String> flux = Flux.just("1", "2", "3").delaySubscription(Duration.ofSeconds(3));

        Flux.concat(late, flux).toIterable().forEach(System.out::println);

    }

}
