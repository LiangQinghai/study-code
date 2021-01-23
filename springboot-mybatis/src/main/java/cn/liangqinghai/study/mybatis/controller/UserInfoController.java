package cn.liangqinghai.study.mybatis.controller;


import cn.liangqinghai.study.mybatis.entity.UserInfo;
import cn.liangqinghai.study.mybatis.service.IUserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    private final IUserInfoService userInfoService;

    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/show-all")
    public List<UserInfo> showAll() {

        return userInfoService.list();

    }

    @GetMapping("/show-all-page")
    public List<UserInfo> showAllPage() {

        return userInfoService.listPage();

    }

}
