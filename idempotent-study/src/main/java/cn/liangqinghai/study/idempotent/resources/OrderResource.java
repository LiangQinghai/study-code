package cn.liangqinghai.study.idempotent.resources;

import cn.liangqinghai.study.idempotent.annotations.Idempotent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangQinghai
 * @Title OrderResource
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 8:12 PM
 */
@RestController
@RequestMapping("/order")
public class OrderResource {

    @PostMapping("/create")
    @ResponseBody
    @Idempotent
    public String order(Integer id, String name, Long money) {

        return id + name + money;

    }

}
