package com.yicj.hello;

import com.yicj.hello.model.vo.UserVO;
import com.yicj.hello.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class WebClientTest {

    @Autowired
    private WebClient client ;

    @Test
    public void post() throws InterruptedException {
        UserVO userVO = new UserVO() ;
        userVO.setName("张三");
        userVO.setAddress("北京");
        UserVO value = client.post()
                .uri("http://localhost:8081/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userVO), UserVO.class)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    return Mono.error(new RuntimeException("服务器内部错误！")) ;
                })
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    return Mono.error(new RuntimeException("客户端出错！")) ;
                })
                .bodyToMono(UserVO.class)
                .block();
        log.info("value : {}", value);
    }
}
