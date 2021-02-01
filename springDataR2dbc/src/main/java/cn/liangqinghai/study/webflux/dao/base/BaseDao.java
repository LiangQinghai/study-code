package cn.liangqinghai.study.webflux.dao.base;

import cn.liangqinghai.study.webflux.domain.User;
import org.reactivestreams.Publisher;
import org.springframework.data.mapping.callback.ReactiveEntityCallbacks;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

/**
 * @author Mr.Liang
 */
@SuppressWarnings("unused")
public abstract class BaseDao<T> {

    /**
     * 实体映射上下文，获取对应映射关系
     */
    @Resource
    private R2dbcMappingContext mappingContext;

    /**
     * 查询模板
     */
    R2dbcEntityTemplate template;

    /**
     * 实体字段映射关系
     */
    private RelationalPersistentEntity<T> relationalPersistentEntity;
    /**
     * 当前字节码
     */
    private Class<T> clazz;

    @Resource
    private void init(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.template = r2dbcEntityTemplate;
        this.clazz = getTypeClass();
        this.relationalPersistentEntity = getRequiredEntity();
//        this.template.setEntityCallbacks(ReactiveEntityCallbacks.create(new BeforeSaveCallback<T>() {
//            @Override
//            public Publisher<T> onBeforeSave(T entity, OutboundRow row, SqlIdentifier table) {
//                if (entity instanceof User) {
//                    User user = (User) entity;
//                    user.setPassword("add----" + user.getPassword());
//                }
//                row.get("password").getValue()
//                return Mono.just(entity);
//            }
//        }));
        this.template.setEntityCallbacks(ReactiveEntityCallbacks.create(
                new BeforeConvertCallback<T>() {
                    @Override
                    public Publisher<T> onBeforeConvert(T entity, SqlIdentifier table) {
                        if (entity instanceof User) {
                            User user = (User) entity;
                            user.setPassword("add----" + user.getPassword());
                        }
                        return Mono.just(entity);
                    }
                }
        ));
    }

    @SuppressWarnings("unchecked")
    Class<T> getTypeClass() {
        return this.clazz == null ?
                (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
                : this.clazz;
    }

    /**
     * 获取实体映射关系
     *
     * @return RelationalPersistentEntity {@link RelationalPersistentEntity}
     */
    @SuppressWarnings("unchecked")
    private RelationalPersistentEntity<T> getRequiredEntity() {

        return this.relationalPersistentEntity == null ?
                (RelationalPersistentEntity<T>) this.mappingContext.getRequiredPersistentEntity(getTypeClass())
                : this.relationalPersistentEntity;

    }

    /**
     * 获取id查询
     *
     * @param id 查询id值
     * @return Query {@link Query}
     */
    Query getIdQuery(Object id) {

        RelationalPersistentEntity<T> requiredEntity = getRequiredEntity();
        if (!requiredEntity.hasIdProperty()) {
            throw new RuntimeException("实体" + getTypeClass().getCanonicalName() + "无id属性");
        }

        return Query.query(Criteria.where(requiredEntity.getRequiredIdProperty().getName()).is(id));

    }

}
