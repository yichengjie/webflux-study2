package com.yicj.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author: yicj
 * @date: 2023/4/22 21:59
 */
@Slf4j
public class HotStreamTest {

    @Test
    public void simpleHotStreamCreation(){
        Sinks.Many<Object> hotSource = Sinks.unsafe().many().multicast().directBestEffort();
        Flux<Object> hotFlux = hotSource.asFlux();
        hotFlux.subscribe(value -> log.info("Subscriber1 get : {}", value)) ;
        hotSource.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        hotSource.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        //
        hotFlux.subscribe(value -> log.info("Subscriber2 get : {}", value)) ;
        hotSource.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        hotSource.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);
        hotSource.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
    }

    @Test
    public void connectableFlux() throws InterruptedException {
        Flux<Integer> source = Flux.range(1, 4);
        ConnectableFlux<Integer> connectableFlux = source.publish();
        connectableFlux.subscribe(value -> log.info("Subscriber1 get : {}", value)) ;
        connectableFlux.subscribe(value -> log.info("Subscriber2 get : {}", value)) ;
        log.info("Finished subscribe action");
        //
        Thread.sleep(1000);
        log.info("Start to connect to flux");
        connectableFlux.connect() ;
    }


    @Test
    public void autoConnectFlux() throws InterruptedException {
        Flux<Integer> source = Flux.range(1, 4);
        Flux<Integer> autoConnect = source.publish().autoConnect(2);
        autoConnect.subscribe(value -> log.info("Subscriber1 get : {}", value)) ;
        autoConnect.subscribe(value -> log.info("Subscriber2 get : {}", value)) ;
        log.info("Finished subscribe action");
    }

}
