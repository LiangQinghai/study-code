package cn.liangqinghai.study.mbp.utils;

import cn.liangqinghai.study.mbp.model.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author LiangQinghai
 * @Title ShiroUtils
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 16:34
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getAdmin() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getAdmin().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 获取Shiro属性
     *
     * @param key Object
     * @return Object
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 获取是否已登录
     *
     * @return boolean
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

//    /**
//     * 获取验证码
//     *
//     * @param key 验证码Key
//     * @return String
//     */
//    public static String getKaptcha(String key) {
//        Object kaptcha = getSessionAttribute(key);
//        if (kaptcha == null) {
//            throw new RRException("验证码已失效");
//        }
//        getSession().removeAttribute(key);
//        return kaptcha.toString();
//    }

}
