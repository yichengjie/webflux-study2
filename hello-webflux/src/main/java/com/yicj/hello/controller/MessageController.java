package com.yicj.hello.controller;

import com.yicj.hello.entity.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author: yicj
 * @date: 2023/4/16 19:27
 */
@RestController
@RequestMapping
public class MessageController {


    @GetMapping("/")
    public Flux<Message> allMessage(){
        return Flux.just(
                Message.builder().body("hello spring 5").build() ,
                Message.builder().body("hello springboot 2").build()
        ) ;
    }
}
