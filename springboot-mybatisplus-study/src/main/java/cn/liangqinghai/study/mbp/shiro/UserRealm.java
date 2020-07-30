package cn.liangqinghai.study.mbp.shiro;

import cn.liangqinghai.study.mbp.model.sys.SysMenu;
import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.service.sys.SysMenuService;
import cn.liangqinghai.study.mbp.service.sys.SysUserService;
import cn.liangqinghai.study.mbp.utils.Constant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author LiangQinghai
 * @Title UserRealm
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 14:32
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private Constant constant;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        Long id = user.getId();

        List<String> permsList = null;

        if (constant.adminId.equals(id)) {

            List<SysMenu> sysMenus = sysMenuService.queryList(new HashMap<>());

            permsList = new ArrayList<>(sysMenus.size());

            for (SysMenu sysMenu : sysMenus) {
                permsList.add(sysMenu.getPerms());
            }

        } else {

            permsList = sysUserService.queryAllPerms(id);

        }

        Set<String> permSet = new HashSet<>();
        for (String s : permsList) {
            if (!StringUtils.isEmpty(s)) {
                permSet.addAll(Arrays.asList(s.split(",")));
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permSet);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        SysUser sysUser = sysUserService.queryByUserName(username);

        if (sysUser == null) {
            throw new UnknownAccountException("用户不存在");
        }

        if (!sysUser.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码不正确");
        }

        return new SimpleAuthenticationInfo(sysUser, password, getName());
    }
}
