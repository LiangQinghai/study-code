package cn.liangqinghai.study.jwt.mapper;

import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.jwt.module.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title UserMapperTest
 * @ProjectName study-code
 * @Description
 * @date 2020/5/19 19:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {

        List<User> users = userMapper.selectList(null);
        users.forEach(v -> System.out.println(JSONUtil.parse(v).toStringPretty()));

    }

}
