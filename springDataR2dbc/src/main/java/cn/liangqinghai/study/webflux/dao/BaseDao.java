package cn.liangqinghai.study.webflux.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDao<T> {

    @Resource
    @Lazy
    private R2dbcEntityTemplate template;

    @Resource
    private R2dbcMappingContext mappingContext;

    Mono<T> getById(Object id) {

        return template.selectOne(getIdQuery(id), getTClass());

    }

    Mono<Integer> deleteById(Object id) {

        return template.delete(getIdQuery(id), getTClass());

    }

    Mono<List<T>> query(Query query) {

        return template.select(query, getTClass())
                .collect(Collectors.toList());

    }

    Mono<T> create(T t) {

        return template.insert(t);

    }

    @SuppressWarnings("unchecked")
    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private RelationalPersistentEntity<T> getRequiredEntity() {

        return (RelationalPersistentEntity<T>) this.mappingContext.getRequiredPersistentEntity(getTClass());

    }

    /**
     * 获取id
     *
     * @param id
     * @return
     */
    private Query getIdQuery(Object id) {

        RelationalPersistentEntity<T> requiredEntity = getRequiredEntity();
        if (!requiredEntity.hasIdProperty()) {
            throw new RuntimeException("无ID");
        }

        return Query.query(Criteria.where(requiredEntity.getRequiredIdProperty().getName()).is(id));

    }

}
