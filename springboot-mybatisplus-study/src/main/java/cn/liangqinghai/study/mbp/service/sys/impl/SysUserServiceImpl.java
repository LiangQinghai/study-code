package cn.liangqinghai.study.mbp.service.sys.impl;

import cn.liangqinghai.study.mbp.dao.sys.SysUserDao;
import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.service.sys.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysUserServiceImpl
 * @ProjectName study-code
 * @Description
 * @date 2020/6/3 20:24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Override
    public SysUser queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }
}
