package com.yicj.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author: yicj
 * @date: 2023/4/16 21:47
 */
@RestController
@RequestMapping("/webflux")
public class HelloWebFluxController {

    @GetMapping("hello")
    public Mono<String> webflux(){

        return Mono.just("Hello") ;
    }

    @GetMapping("/sleep/{time}")
    public Mono<String> sleep(@PathVariable("time") int time){
        return Mono.delay(Duration.ofMillis(time))
                .thenReturn("Sleep " + time + "ms, Current Time:" + System.currentTimeMillis());
    }
}
