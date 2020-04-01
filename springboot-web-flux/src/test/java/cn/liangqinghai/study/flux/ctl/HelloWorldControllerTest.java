package cn.liangqinghai.study.flux.ctl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

/**
 * @author LiangQinghai
 * @Title HelloWorldControllerTest
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 2:32 PM
 */
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void hello() throws Exception {
        webTestClient.get()
                .uri(new URI("/hello/world"))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

}
