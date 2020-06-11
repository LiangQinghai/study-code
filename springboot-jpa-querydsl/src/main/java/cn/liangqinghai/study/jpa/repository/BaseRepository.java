package cn.liangqinghai.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author LiangQinghai
 * @Title BaseRepository
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 19:54
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {
}
