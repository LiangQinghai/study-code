package cn.liangqinghai.study.webflux.dao;

import cn.liangqinghai.study.webflux.dao.base.CrudDao;
import cn.liangqinghai.study.webflux.domain.User;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class UserDao extends CrudDao<User> {

    public Mono<List<User>> findAll() {

        return query(Query.empty());

    }

    public Flux<User> saveAll(List<User> users) {

        return create(users);

    }

}
