package cn.liangqinghai.study.flux.service;

import cn.liangqinghai.study.flux.entities.HelloWorld;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author LiangQinghai
 * @Title HelloWorldService
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 3:46 PM
 */
public interface IHelloWorldService {

    Flux<HelloWorld> list();

    Flux<HelloWorld> findByIds(Flux<Long> ids);

    Mono<HelloWorld> getById(Long id);

    Mono<HelloWorld> createOrUpdate(HelloWorld helloWorld);

    Mono<HelloWorld> delete(Long id);

}
