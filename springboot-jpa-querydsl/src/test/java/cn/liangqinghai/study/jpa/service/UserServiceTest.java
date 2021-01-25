package cn.liangqinghai.study.jpa.service;

import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.jpa.dto.UserDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.PredicateOperation;
import com.querydsl.core.types.dsl.Expressions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title UserServiceTest
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 14:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testOne() {

        List<UserDto> dto = userService.selectAllUserDto();

        System.out.println(JSONUtil.toJsonPrettyStr(dto));

    }

    @Test
    public void testTwo() {

        PredicateOperation predicate = ExpressionUtils.predicate(Ops.EQ, Expressions.stringPath("a=1"));

        predicate.getOperator();

    }

}
