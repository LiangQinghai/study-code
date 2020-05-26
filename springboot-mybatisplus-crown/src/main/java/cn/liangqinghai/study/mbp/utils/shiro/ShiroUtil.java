package cn.liangqinghai.study.mbp.utils.shiro;

import cn.liangqinghai.study.mbp.domain.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * @author LiangQinghai
 * @Title shiro
 * @ProjectName study-code
 * @Description
 * @date 2020/5/26 20:18
 */
public final class ShiroUtil {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubject().logout();
    }

    public static User  getSysUser() {

        User user = null;

        Object principal = getSubject().getPrincipal();

        if (Objects.nonNull(principal)) {
            user = new User();
            BeanUtils.copyProperties(user, principal);
        }

        return user;

    }

    public static void setSysUser(User user) {

        Subject subject = getSubject();

        PrincipalCollection principals = subject.getPrincipals();

        String realName = principals.getRealmNames().iterator().next();

        SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(user, realName);

        subject.runAs(principalCollection);

    }

    public static void clearCacheAuthorizationInfo() {

        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();

        UserRealm userRealm = (UserRealm) rsm.getRealms().iterator().next();

        userRealm.clearCachedAuthorizationInfo();

    }

    public static Long getUserId() {
        return 1L;
    }

    public static String getLoginName() {
        return "";
    }

    public static String getIp() {
        return "";
    }

    public static String getSessionId() {
        return "";
    }

}
