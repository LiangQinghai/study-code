package cn.liangqinghai.study.webflux.dao.base;

import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webflux.dao.base</p>
 * <p>File name: CrudDao</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/30 10:52
 */
@SuppressWarnings("unused")
public abstract class CrudDao<T> extends BaseDao<T>{

    /**
     * 根据id获取
     *
     * @param id id
     * @return 结果
     */
    protected Mono<T> getById(Object id) {

        return template.selectOne(getIdQuery(id), getTypeClass());

    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 影响结果数
     */
    protected Mono<Integer> deleteById(Object id) {

        return template.delete(getIdQuery(id), getTypeClass());

    }

    /**
     * 根据查询条件查询
     *
     * @param query 查询条件
     * @return 结果集
     */
    protected Mono<List<T>> query(Query query) {

        return template.select(query, getTypeClass())
                .collect(Collectors.toList());

    }

    /**
     * 插入一条数据
     *
     * @param t 对象
     * @return 结果
     */
    protected Mono<T> create(T t) {

        return template.insert(t);

    }

    protected Flux<T> create(List<T> ts) {

        Flux<T> res = Flux.empty();
        ts.stream()
                .map(this::create)
                .forEach(res::mergeWith);
        return res;

    }

}
