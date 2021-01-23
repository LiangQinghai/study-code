package cn.liangqinghai.study.webflux.dao;

import cn.liangqinghai.study.webflux.domain.UserInfoPo;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webflux.dao</p>
 * <p>File name: UserInfoDao</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/23 13:10
 */
@Repository
public class UserInfoDao extends BaseDao<UserInfoPo>{

    public Mono<List<UserInfoPo>> list() {

        return query(Query.empty());

    }

    public Mono<List<UserInfoPo>> listPage() {

        return query(Query.empty().limit(10).offset(0));

    }

}
