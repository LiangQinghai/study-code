package cn.liangqinghai.study.jpa.service.impl;

import cn.liangqinghai.study.jpa.annos.EnableTenant;
import cn.liangqinghai.study.jpa.dto.UserDto;
import cn.liangqinghai.study.jpa.model.QUser;
import cn.liangqinghai.study.jpa.model.User;
import cn.liangqinghai.study.jpa.service.UserService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title UserServiceImpl
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 11:44
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JPAQueryFactory factory;

    @Override
    @EnableTenant
    public List<UserDto> selectAllUserDto() {

        QUser user = QUser.user;

        List<User> fetch = factory.selectFrom(user)
                .where(user.id.isNotNull())
                .fetch();

        List<UserDto> dtos = new ArrayList<>();
        BeanUtils.copyProperties(fetch, dtos);

        dtos = factory.select(Projections.fields(UserDto.class, user.id, user.userName))
                .from(user)
                .where(user.id.isNotNull())
                .fetch();

        return dtos;

    }
}
