package cn.liangqinghai.study.security.service.impl;

import cn.liangqinghai.study.security.auth.bean.AuthUserDetails;
import cn.liangqinghai.study.security.po.UserPO;
import cn.liangqinghai.study.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.service.impl</p>
 * <p>File name: UserDetailServiceImpl</p>
 * <div>
 * <h3>Description: </h3>
 * 查询用户
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 15:55
 */
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO po = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户名/密码错误"));
        return new AuthUserDetails(po, new ArrayList<>());
    }
}
