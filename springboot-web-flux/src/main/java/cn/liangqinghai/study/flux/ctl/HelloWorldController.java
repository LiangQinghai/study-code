package cn.liangqinghai.study.flux.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author LiangQinghai
 * @Title HelloWorldController
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 2:21 PM
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello/{who}")
    public Mono<String> hello(@PathVariable String who) {
        return Mono.just(who)
                .map(s -> "Hello," + who);
    }

}
