package com.yicj.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

/**
 * @author: yicj
 * @date: 2023/4/22 21:46
 */
@Slf4j
public class StreamParallelFluxTest {

    @Test
    // 注意parallel 需要设置runOn，否则还是在订阅线程上运行
    public void parallelFlux(){
        Flux<Integer> flux = Flux.range(1, 20);
        ParallelFlux<Integer> parallelFlux = flux.parallel().runOn(Schedulers.parallel());
        parallelFlux.subscribe(i -> log.info("print {} on {}", i , Thread.currentThread().getName())) ;
    }

    @Test
    // 注意parallel 需要设置runOn，否则还是在订阅线程上运行, 调用过sequential方法，这里将会在一个线程上执行
    public void parallelFlux2(){
        Flux<Integer> flux = Flux.range(1, 20);
        Flux<Integer> normalizedFlux = flux.parallel().runOn(Schedulers.parallel())
                .map(i -> i + 1)
                .sequential();
        normalizedFlux.subscribe(i -> log.info("print {} on {}", i , Thread.currentThread().getName())) ;
    }
}
