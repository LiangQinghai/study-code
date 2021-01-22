package cn.liangqinghai.study.webflux.dao;

import cn.liangqinghai.study.webflux.domain.User;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User>{

    public Mono<List<User>> findAll() {

        return query(Query.empty());

    }

}
