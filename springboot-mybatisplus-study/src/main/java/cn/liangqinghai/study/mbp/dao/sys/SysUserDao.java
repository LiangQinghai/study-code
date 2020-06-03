package cn.liangqinghai.study.mbp.dao.sys;

import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.utils.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysUserDao
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:27
 */
public interface SysUserDao extends BaseDao<SysUser> {

    List<String> queryAllPerms(Long userId);

    @Select("<script>" +
            "SELECT sm.ID\n" +
            "FROM sys_user_role sur\n" +
            "      LEFT JOIN sys_role_menu srm on sur.ROLE_ID = srm.ROLE_ID\n" +
            "      LEFT JOIN sys_menu sm ON sm.ID = srm.MENU_ID\n" +
            "WHERE sur.USER_ID = #{userId}" +
            "</script>")
    List<Long> queryAllMenuId(@Param("userId") Long userId);

    SysUser queryByUserName(String username);

}
