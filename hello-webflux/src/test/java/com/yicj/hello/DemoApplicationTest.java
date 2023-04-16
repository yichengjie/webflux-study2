package com.yicj.hello;

import com.yicj.hello.controller.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author: yicj
 * @date: 2023/4/16 19:30
 */
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = MessageController.class)
public class DemoApplicationTest {

    @Autowired
    private WebTestClient client ;


    @Test
    public void getAllMessagesShouldBeOk(){
        client.get().uri("/").exchange().expectStatus().isOk() ;
    }

}
