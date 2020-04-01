package cn.liangqinghai.study.nutz.ctl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author LiangQinghai
 * @Title HelloWorldControllerTest
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 2:44 PM
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/hello"));

        Assertions.assertEquals(perform.andReturn().getResponse().getStatus(), HttpStatus.OK.value());
    }

}
