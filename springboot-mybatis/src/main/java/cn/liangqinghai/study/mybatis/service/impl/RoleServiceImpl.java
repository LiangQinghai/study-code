package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.Role;
import cn.liangqinghai.study.mybatis.mapper.RoleMapper;
import cn.liangqinghai.study.mybatis.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
