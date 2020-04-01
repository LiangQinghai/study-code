package cn.liangqinghai.study.flux.service.impl;

import cn.liangqinghai.study.flux.entities.HelloWorld;
import cn.liangqinghai.study.flux.service.IHelloWorldService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiangQinghai
 * @Title HelloWorldService
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 3:52 PM
 */
@Service
public class HelloWorldService implements IHelloWorldService {

    private final ConcurrentHashMap<Long, HelloWorld> repository = new ConcurrentHashMap<>();

    @Override
    public Flux<HelloWorld> list() {
        return Flux.fromIterable(repository.values());
    }

    @Override
    public Flux<HelloWorld> findByIds(Flux<Long> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(repository.get(id)));
    }

    @Override
    public Mono<HelloWorld> getById(Long id) {
        return Mono.justOrEmpty(repository.get(id)).switchIfEmpty(Mono.error(new Exception("Id: " + id + " Not Found")));
    }

    @Override
    public Mono<HelloWorld> createOrUpdate(HelloWorld helloWorld) {
        repository.put(helloWorld.getId(), helloWorld);
        return Mono.just(helloWorld);
    }

    @Override
    public Mono<HelloWorld> delete(Long id) {
        return Mono.justOrEmpty(repository.remove(id));
    }
}
