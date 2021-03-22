package cn.liangqinghai.study.security.auth.bean;

import cn.hutool.core.util.StrUtil;
import cn.liangqinghai.study.security.po.PermissionPO;
import cn.liangqinghai.study.security.po.UserPO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.auth.bean</p>
 * <p>File name: AuthUserDetails</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 15:20
 */
public class AuthUserDetails implements UserDetails {

    private static final long serialVersionUID = 8191745808565250742L;
    private final UserPO user;
    private final List<PermissionPO> permissions;

    public AuthUserDetails(UserPO user, List<PermissionPO> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .filter(p -> StrUtil.isNotEmpty(p.getValue()))
                .map(p -> new SimpleGrantedAuthority(p.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
