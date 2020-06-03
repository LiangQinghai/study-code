package cn.liangqinghai.study.mbp.shiro;

import cn.liangqinghai.study.mbp.model.sys.SysMenu;
import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.service.sys.SysMenuService;
import cn.liangqinghai.study.mbp.service.sys.SysUserService;
import cn.liangqinghai.study.mbp.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title UserRealm
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 14:32
 */
@Slf4j
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

            permsList = null;

        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
