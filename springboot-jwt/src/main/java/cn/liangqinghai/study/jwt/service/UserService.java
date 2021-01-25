package cn.liangqinghai.study.jwt.service;

import cn.liangqinghai.study.jwt.module.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Liang
 * @date 2020/4/19
 */
@Service
public class UserService {

    private Map<String, User> users = new HashMap<String, User>(){

        private static final long serialVersionUID = 7044326576844394627L;

        {
            put("1", new User().setId(1).setName("one").setPassword("123456").setAge(18));
            put("2", new User().setId(2).setName("two").setPassword("123456").setAge(18));
            put("3", new User().setId(3).setName("three").setPassword("123456").setAge(18));
            put("4", new User().setId(4).setName("four").setPassword("123456").setAge(18));
            put("5", new User().setId(5).setName("five").setPassword("123456").setAge(18));
            put("6", new User().setId(6).setName("six").setPassword("123456").setAge(18));
        }
    };

    /**
     * id 获取user
     *
     * @param id
     * @return
     */
    public User getById(String id) {

        if (StringUtils.isEmpty(id)) {
            return null;
        }

        return users.get(id);

    }

    public User login(User user) {

        if (user == null || user.getId() == null || StringUtils.isEmpty(user.getPassword())) {
            return null;
        }

        User user1 = users.get(user.getId() + "");
        if (!user1.getPassword().equals(user.getPassword())) {
            return null;
        }
        return user1;
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

}
