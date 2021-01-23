package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.UserPassword;
import cn.liangqinghai.study.mybatis.mapper.UserPasswordMapper;
import cn.liangqinghai.study.mybatis.service.IUserPasswordService;
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
public class UserPasswordServiceImpl extends ServiceImpl<UserPasswordMapper, UserPassword> implements IUserPasswordService {

}
