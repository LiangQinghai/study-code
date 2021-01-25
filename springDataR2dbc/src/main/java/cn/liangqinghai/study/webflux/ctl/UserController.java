package cn.liangqinghai.study.webflux.ctl;

import cn.liangqinghai.study.webflux.dao.UserDao;
import cn.liangqinghai.study.webflux.dao.UserInfoDao;
import cn.liangqinghai.study.webflux.domain.User;
import cn.liangqinghai.study.webflux.domain.UserInfoPo;
import cn.liangqinghai.study.webflux.service.IUserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private final UserDao userDao;
    private final UserInfoDao userInfoDao;

    public UserController(IUserService userService,
                          UserDao userDao,
                          UserInfoDao userInfoDao) {
        this.userService = userService;
        this.userDao = userDao;
        this.userInfoDao = userInfoDao;
    }

    @GetMapping("/list")
    public Mono<List<User>> list() {
        return userDao.findAll();
    }

    @PostMapping("/save")
    public Mono<User> save(User user) {
        return userService.save(user);
    }

    @PutMapping("/update")
    public Mono<User> update(User user) {
        return userService.update(user);
    }

    @GetMapping("findAll")
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("getById")
    public Mono<User> getById(Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteById(Long id) {
        return userService.deleteById(id);
    }


    @GetMapping("/list-po")
    public Mono<List<UserInfoPo>> listPo () {
        return userInfoDao.list();
    }

    @GetMapping("/list-po-page")
    public Mono<List<UserInfoPo>> listPoPage () {
        return userInfoDao.listPage();
    }

}
