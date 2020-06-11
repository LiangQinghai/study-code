package cn.liangqinghai.study.jpa.model;

import cn.liangqinghai.study.jpa.common.ContextHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title TaskPoTest
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 11:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
@EnableTransactionManagement
public class TaskPoTest {

    @Autowired
    private JPAQueryFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testOne() {

        Filter filter = entityManager.unwrap(Session.class).enableFilter("TENANT_FILTER");

        filter.setParameter("tenantCode", ContextHolder.tenantCode);

        QTaskPo taskPo = QTaskPo.taskPo;

        List<TaskPo> fetch = factory.selectFrom(taskPo)
                .where(taskPo.id.isNotNull())
                .fetch();

        System.out.println(Arrays.toString(fetch.toArray()));

    }

}
