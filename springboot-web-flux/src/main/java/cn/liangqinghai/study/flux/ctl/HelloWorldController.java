package cn.liangqinghai.study.flux.ctl;

import cn.liangqinghai.study.flux.entities.HelloWorld;
import cn.liangqinghai.study.flux.service.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title HelloWorldController
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 2:21 PM
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    IHelloWorldService helloWorldService;

    @GetMapping("/{who}")
    public Mono<String> hello(@PathVariable String who) {
        return Mono.just(who)
                .map(s -> "Hello," + who);
    }

    @GetMapping("/list")
    public Flux<HelloWorld> list() {
        return helloWorldService.list();
    }

    @GetMapping("/findByIds")
    public Flux<HelloWorld> findByIds(List<Long> ids) {
        return helloWorldService.findByIds(Flux.fromIterable(ids));
    }

    @GetMapping("getById")
    public Mono<HelloWorld> getById(Long id) {
        return helloWorldService.getById(id);
    }

}
