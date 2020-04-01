package cn.liangqinghai.study.flux.ctl;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * @author LiangQinghai
 * @Title SseControllerTest
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 7:20 PM
 */
public class SseControllerTest {

    @Test
    public void test() {

        WebClient webClient = WebClient.create();

        webClient.get()
                .uri("http://localhost:8989/sse/random")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.body(BodyExtractors.toFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
                })))
                .filter(sse -> Objects.nonNull(sse.data()))
                .map(ServerSentEvent::data)
                .buffer(10)
                .doOnNext(System.out::println)
                .blockFirst();

    }

}
