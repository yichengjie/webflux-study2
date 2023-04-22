package com.yicj.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author: yicj
 * @date: 2023/4/22 21:30
 */
@Slf4j
public class StreamSchedulerNewTest {

    @Test
    public void simpleSubscribeOn() throws InterruptedException {
        Scheduler scheduler = Schedulers.newElastic("subscribeOn-demo-elastic");
        Flux<Integer> flux = Flux.range(1, 4)
                .filter(i -> {
                    log.info("filter in thread {}", Thread.currentThread().getName());
                    return i % 2 == 0;
                })
                .subscribeOn(scheduler)
                .map(i -> {
                    log.info("map in thread {}", Thread.currentThread().getName());
                    return i + 2;
                });
        Thread t = new Thread(() -> {
            log.info("start current thread");
            flux.subscribe(i -> log.info("value : {}", i)) ;
            log.info("end current thread");
        }) ;
        t.start();
        t.join();
    }

    @Test
    public void simplePublishOn() throws InterruptedException {
        Scheduler publishScheduler = Schedulers.newElastic("publishOn-demo-elastic");
        Flux<Integer> flux = Flux.range(1, 4)
                .filter(i -> {
                    log.info("filter in thread {}", Thread.currentThread().getName());
                    return i % 2 == 0;
                })
                .publishOn(publishScheduler)
                .map(i -> {
                    log.info("map in thread {}", Thread.currentThread().getName());
                    return i + 2;
                });
        Thread t = new Thread(() -> {
            log.info("start current thread");
            flux.subscribe(i -> log.info("value : {}", i)) ;
            log.info("end current thread");
        }) ;
        t.start();
        t.join();
    }

    @Test
    public void simplePublishOnAndSubscribeOn() throws InterruptedException {
        Scheduler subscribeScheduler = Schedulers.newElastic("subscribeOn-demo-elastic");
        Scheduler publishScheduler = Schedulers.newElastic("publishOn-demo-elastic");
        Flux<Integer> flux = Flux.range(1, 4)
                .subscribeOn(subscribeScheduler)
                .filter(i -> {
                    log.info("filter in thread {}", Thread.currentThread().getName());
                    return i % 2 == 0;
                })
                .publishOn(publishScheduler)
                .map(i -> {
                    log.info("map in thread {}", Thread.currentThread().getName());
                    return i + 2;
                });
        Thread t = new Thread(() -> {
            log.info("start current thread");
            flux.subscribe(i -> log.info("value : {}", i)) ;
            log.info("end current thread");
        }) ;
        t.start();
        t.join();
        // subscribeOn 从源头开始影响
        // publishOn 只影响后面的操作
    }
}
