package cn.liangqinghai.study.jpa.service;

import cn.liangqinghai.study.jpa.dto.UserDto;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title UserService
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 11:16
 */
public interface UserService {

    List<UserDto> selectAllUserDto();

}
