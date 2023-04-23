package com.yicj.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * @author: yicj
 * @date: 2023/4/23 16:48
 */
@RestController
public class ApiController {

    @GetMapping("/api/prices")
    public Flux<String> prices(){
        return Flux.fromIterable(Arrays.asList("items-1", "items-2")) ;
    }
}
