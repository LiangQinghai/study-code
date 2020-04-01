package cn.liangqinghai.study.flux.ctl;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author LiangQinghai
 * @Title SseController
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 6:58 PM
 */
@RestController
@RequestMapping("/sse")
public class SseController {

    @GetMapping("/random")
    public Flux<ServerSentEvent<Integer>> random() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder().event("random").id(Long.toString(data.getT1()))
                .data(data.getT2()).build());

    }

}
