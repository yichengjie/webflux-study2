package com.yicj.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author: yicj
 * @date: 2023/4/23 17:03
 */
@Slf4j
@SpringBootTest
public class WebTestClientTest2 {

    private WebTestClient client ;

    @BeforeEach
    public void setup(ApplicationContext context){
        this.client = WebTestClient.bindToApplicationContext(context).build() ;
    }

    @Test
    public void bindToApplicationContext(){
        client.get()
                .uri("/api/prices")
                .exchange()
                .expectBody()
                .consumeWith(value -> log.info("value : {}", value)) ;
    }

}
