package com.yicj.hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author yicj
 * @date 2023年04月18日 20:32
 */
@Slf4j
@RestController
@RequestMapping("/asyncAndSync")
public class AsyncAndSyncController {

    @Autowired
    private Executor myTaskPool ;

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
        // 类似这种同步的话，因为处理线程只有cpu * 2 个，则需要非常久的时间, 所以这种同步写法是致命错误
//        Mono<String> result = Mono.fromSupplier(this::execute)
//                .subscribeOn(Schedulers.fromExecutor(myTaskPool));
         Mono<String> result = Mono.defer(()->
                 Mono.fromCallable(this::execute).subscribeOn(Schedulers.fromExecutor(myTaskPool)));
        // 后面这两种写法是正确的
        //Mono<String> result = Mono.fromSupplier(this::execute2).delayElement(Duration.ofMillis(2));
        // Mono<String> result = Mono.delay(Duration.ofMillis(2)).thenReturn(this.execute2());
        log.info("sync method end");
        return result;
    }

    // 阻塞代码
    private String execute() {
        log.info("execute ..");
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
