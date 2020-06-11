package cn.liangqinghai.study.jpa.controller;

import cn.liangqinghai.study.jpa.dto.UserDto;
import cn.liangqinghai.study.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title UserController
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 12:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public List<UserDto> listUsers() {
        return userService.selectAllUserDto();
    }

}
