package cn.liangqinghai.study.security.repository;

import cn.liangqinghai.study.security.po.UserPO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.repository</p>
 * <p>File name: UserRepository</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 15:57
 */
@Repository
public class UserRepository {

    private static final List<UserPO> USERS = new ArrayList<>();

    static {

        USERS.add(
                new UserPO()
                        .setId(1L)
                        .setName("admin")
                        // 123456
                        .setPassword("$2a$10$mSuRZxysx1vc26DhoS0QJepJSahOgp.ztv.xm0HP8CTxCrxshgYOu")
        );

        USERS.add(
                new UserPO()
                        .setId(2L)
                        .setName("two")
                        // 123456
                        .setPassword("$2a$10$mSuRZxysx1vc26DhoS0QJepJSahOgp.ztv.xm0HP8CTxCrxshgYOu")
        );

        USERS.add(
                new UserPO()
                        .setId(3L)
                        .setName("three")
                        // 123456
                        .setPassword("$2a$10$mSuRZxysx1vc26DhoS0QJepJSahOgp.ztv.xm0HP8CTxCrxshgYOu")
        );

    }

    public Optional<UserPO> findByUsername(String username) {

        return USERS.stream()
                .filter(s -> s.getName().equals(username))
                .findFirst();


    }

}
