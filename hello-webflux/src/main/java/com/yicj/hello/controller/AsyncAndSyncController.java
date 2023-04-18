package com.yicj.hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author yicj
 * @date 2023年04月18日 20:32
 */
@Slf4j
@RestController
@RequestMapping("/asyncAndSync")
public class AsyncAndSyncController {

    @GetMapping("/sync")
    public String sync(){
        log.info("sync method start");
        String result = this.execute() ;
        log.info("sync method end");
        return result ;
    }

    @GetMapping("/async")
    public Mono<String> asyncMono(){
        log.info("sync method start");
        // Mono<String> result = Mono.fromSupplier(this::execute);
        //Mono<String> result = Mono.fromSupplier(this::execute2).delayElement(Duration.ofMillis(2));
        Mono<String> result = Mono.delay(Duration.ofMillis(2)).thenReturn(this.execute2());
        log.info("sync method end");
        return result;
    }

    private String execute() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" ;
    }

    private String execute2() {
        return "hello" ;
    }
}
