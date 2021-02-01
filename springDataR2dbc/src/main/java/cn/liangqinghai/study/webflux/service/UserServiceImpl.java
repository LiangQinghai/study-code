package cn.liangqinghai.study.webflux.service;

import cn.liangqinghai.study.webflux.dao.UserDao;
import cn.liangqinghai.study.webflux.domain.User;
import cn.liangqinghai.study.webflux.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserDao userDao;

    public UserServiceImpl(UserRepository userRepository,
                           UserDao userDao) {
        this.userRepository = userRepository;
        this.userDao = userDao;
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
    public Flux<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Flux<User> saveAllWithDao(List<User> users) {
        return userDao.saveAll(users);
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
