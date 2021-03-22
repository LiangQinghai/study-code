package cn.liangqinghai.study.security.service.impl;

import cn.liangqinghai.study.security.service.IUserService;
import cn.liangqinghai.study.security.utils.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.service.impl</p>
 * <p>File name: UserServiceImpl</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 16:26
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDetailsService userDetailsService,
                           PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("用户名密码错误");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return JwtUtil.genToken(userDetails);

    }
}
