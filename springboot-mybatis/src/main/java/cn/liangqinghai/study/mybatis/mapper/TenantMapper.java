package cn.liangqinghai.study.mybatis.mapper;

import cn.liangqinghai.study.mybatis.entity.Tenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 租户表，只有超级管理员能看到 Mapper 接口
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
public interface TenantMapper extends BaseMapper<Tenant> {

}
