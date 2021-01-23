package cn.liangqinghai.study.mybatis.service;

import cn.liangqinghai.study.mybatis.entity.UserInfo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
public interface IUserInfoService extends IService<UserInfo> {

    List<UserInfo> listPage();

}
