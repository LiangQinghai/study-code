package cn.liangqinghai.study.security.controller;

import cn.liangqinghai.study.security.api.ResultDTO;
import cn.liangqinghai.study.security.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.controller</p>
 * <p>File name: UserController</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 16:31
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultDTO<String> login(String username, String password) {

        return ResultDTO.success(userService.login(username, password));

    }
}
