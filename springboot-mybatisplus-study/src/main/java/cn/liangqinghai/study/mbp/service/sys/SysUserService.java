package cn.liangqinghai.study.mbp.service.sys;

import cn.liangqinghai.study.mbp.model.sys.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysUserService
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 15:33
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * queryByUserName
     *
     * @param username
     * @return
     */
    SysUser queryByUserName(String username);

    List<String> queryAllPerms(Long userId);

    List<Long> queryAllMenuId(Long userId);

}
