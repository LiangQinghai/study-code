package cn.liangqinghai.study.webflux.service;

import cn.liangqinghai.study.webflux.domain.User;
import cn.liangqinghai.study.webflux.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository
                .save(user)
                .map(it -> {
                    if (StringUtils.isEmpty(it.getName()) || StringUtils.isEmpty(it.getPassword())) {
                        throw new IllegalArgumentException("empty");
                    }
                    return it;
                });
    }

    @Override
    public Mono<User> update(User user) {
        return userRepository
                .save(user)
                .map(it -> {
                    if (StringUtils.isEmpty(it.getName()) || StringUtils.isEmpty(it.getPassword())) {
                        throw new IllegalArgumentException("empty");
                    }
                    return it;
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}
