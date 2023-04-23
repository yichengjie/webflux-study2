package com.yicj.hello;

import com.yicj.hello.controller.SubscriptionController;
import com.yicj.hello.entity.StockSubscription;
import com.yicj.hello.service.RSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author: yicj
 * @date: 2023/4/23 16:20
 */
@Slf4j
@WebFluxTest(value = SubscriptionController.class)
public class SubscriptionControllerTest {

    @MockBean
    private RSubscriptionService subscriptionService ;

    @Autowired
    private WebTestClient webTestClient ;

    @Test
    public void findByEmail(){
        Mockito.when(subscriptionService.findByEmail(anyString()))
                .thenReturn(buildSubscriptionList()) ;
        this.webTestClient
                .get()
                .uri("/subscriptions?email=test@qq.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(body -> log.info("body : {}", new String(body.getResponseBody())));
    }

    private Flux<StockSubscription> buildSubscriptionList() {
        return Flux.fromIterable(Arrays.asList(
                StockSubscription.builder()
                        .email("test@qq.com")
                        .symbol("Apple")
                        .build()
        )) ;
    }

}
