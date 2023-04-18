package com.yicj.hello;

import com.yicj.hello.component.CustomExceptionHandler;
import com.yicj.hello.controller.MessageController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author: yicj
 * @date: 2023/4/16 19:30
 */
@Slf4j
//@RunWith(SpringRunner.class)
@WebFluxTest(controllers = MessageController.class)
public class DemoApplicationTest {

    @Autowired
    private WebTestClient client ;

    @Autowired
    private CustomExceptionHandler exceptionHandler ;


    @Test
    public void getAllMessagesShouldBeOk(){
        client.get().uri("/").exchange().expectStatus().isOk() ;
        log.info("-------------");
    }

}
