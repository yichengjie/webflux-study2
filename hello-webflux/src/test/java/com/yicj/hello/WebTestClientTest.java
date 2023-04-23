package com.yicj.hello;

import com.yicj.hello.controller.ApiController;
import com.yicj.hello.router.BookRouter;
import com.yicj.hello.router.RouterConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author: yicj
 * @date: 2023/4/23 16:47
 */
@Slf4j
public class WebTestClientTest {

    @Test
    public void bindToApiController(){
        WebTestClient client = WebTestClient.bindToController(new ApiController()).build() ;
        client.get()
                .uri("/api/prices")
                .exchange()
                .expectBody()
                .consumeWith(value -> log.info("value : {}", value)) ;

    }


    @Test
    public void bindToRouteFunction(){
        WebTestClient client = WebTestClient.bindToRouterFunction(RouterConfig.apiRoute()).build();
        client.get()
                .uri("/api/prices")
                .exchange()
                .expectBody()
                .consumeWith(value -> log.info("value : {}", value)) ;
    }



    @Test
    public void bindToExternalServer(){
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080/").build() ;
        client.post()
                .uri("/api/price")
                .exchange()
                .expectBody()
                .consumeWith(value -> log.info("value : {}", value)) ;
    }

}
