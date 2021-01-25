package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.Tenant;
import cn.liangqinghai.study.mybatis.mapper.TenantMapper;
import cn.liangqinghai.study.mybatis.service.ITenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户表，只有超级管理员能看到 服务实现类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

}
