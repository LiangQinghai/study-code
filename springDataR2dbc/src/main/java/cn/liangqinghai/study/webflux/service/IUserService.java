package cn.liangqinghai.study.webflux.service;

import cn.liangqinghai.study.webflux.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Mr.Liang
 */
public interface IUserService {

    Mono<User> getById(Long id);

    Flux<User> findAll();

    Mono<User> save(User user);

    Flux<User> saveAll(List<User> users);

    Flux<User> saveAllWithDao(List<User> users);

    Mono<User> update(User user);

    Mono<Void> deleteById(Long id);

}
