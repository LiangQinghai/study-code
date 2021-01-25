package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.RoleUser;
import cn.liangqinghai.study.mybatis.mapper.RoleUserMapper;
import cn.liangqinghai.study.mybatis.service.IRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {

}
