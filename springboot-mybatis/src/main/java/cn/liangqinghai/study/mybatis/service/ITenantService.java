package cn.liangqinghai.study.mybatis.service;

import cn.liangqinghai.study.mybatis.entity.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 租户表，只有超级管理员能看到 服务类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
public interface ITenantService extends IService<Tenant> {

}
