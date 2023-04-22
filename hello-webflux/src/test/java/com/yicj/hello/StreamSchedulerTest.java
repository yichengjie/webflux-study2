package com.yicj.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author: yicj
 * @date: 2023/4/22 20:37
 */
@Slf4j
public class StreamSchedulerTest {

    @Test
    public void schedulerElastic(){
        Mono<String> mono = Mono.just("foo");
        mono.map(str -> str + " with scheduler defined")
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(value -> log.info("value -> {}",value)) ;
        // 一般可以用来包装阻塞代码
        // if I need to wrap a blocking call
        Mono<String> blockingCall = Mono.fromCallable(() -> {
            // a blocking call to downstream
            return "result";
        });
        Mono<String> wrappedBlockingCall = blockingCall.subscribeOn(Schedulers.boundedElastic());
    }
}
