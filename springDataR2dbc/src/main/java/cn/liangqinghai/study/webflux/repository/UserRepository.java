package cn.liangqinghai.study.webflux.repository;

import cn.liangqinghai.study.webflux.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
