package cn.liangqinghai.study.mbp.dao.sys;

import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.utils.dao.BaseDao;

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

    List<Long> queryAllMenuId(Long userId);

}
