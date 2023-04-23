package com.yicj.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * @author: yicj
 * @date: 2023/4/23 15:01
 */
@Slf4j
public class StreamUnitTest {

    @Test
    public void simpleStepVerifier(){
        Flux<Integer> source = Flux.range(1,7)
                .filter(i -> i %2 == 1)
                .map(i -> i *10) ;
        //
        StepVerifier.create(source)
                .expectNext(10)
                .expectNextMatches(i -> i %10 == 0)
                .expectNext(50, 70)
                .expectComplete()
                .verify() ;
    }

    @Test
    public void simpleStepVerifierException(){
        Flux<Integer> source = Flux.range(1,7)
                .filter(i -> i %2 == 1)
                .map(i -> i *10) ;
        Flux<Integer> errSource = source.concatWith(Mono.error(new RuntimeException("test ex")));
        //
        errSource.collectList()
            .map(list -> {
                        log.info("list : {}", list);
                        log.info("----next --------");
                        return "success" ;
                    }).subscribe() ;
        log.info("over ...");
//        StepVerifier.create(errSource)
//                .expectNext(10)
//                .expectNextMatches(i -> i %10 == 0)
//                .expectNext(50, 70)
//                .expectComplete()
//                .verify() ;
    }


}
