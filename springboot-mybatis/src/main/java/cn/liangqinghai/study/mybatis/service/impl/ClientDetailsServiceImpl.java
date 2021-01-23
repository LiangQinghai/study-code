package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.ClientDetails;
import cn.liangqinghai.study.mybatis.mapper.ClientDetailsMapper;
import cn.liangqinghai.study.mybatis.service.IClientDetailsService;
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
public class ClientDetailsServiceImpl extends ServiceImpl<ClientDetailsMapper, ClientDetails> implements IClientDetailsService {

}
