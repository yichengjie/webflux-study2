package com.yicj.hello.remote.webmvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class HelloRpcTest {

    @Autowired
    private HelloRpc helloRpc ;

    @Test
    public void save() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1) ;
        String name = "张三" ;
        String address = "BJS" ;
        helloRpc.save(name, address)
                .subscribe(value -> {
                    log.info("user vo : {}", value) ;
                    latch.countDown();
                });
        latch.await();
    }

}
