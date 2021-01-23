package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.AccessLog;
import cn.liangqinghai.study.mybatis.mapper.AccessLogMapper;
import cn.liangqinghai.study.mybatis.service.IAccessLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLog> implements IAccessLogService {

}
