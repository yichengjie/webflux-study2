package com.yicj.hello.controller;

import com.yicj.hello.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author: yicj
 * @date: 2023/4/16 19:19
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/index")
    public String hello() {
        return "Hello, WebFlux !";
    }


    @GetMapping("/user")
    public Mono<User> getUser(){
        User user = new User() ;
        user.setName("张三");
        user.setDesc("webflux 学习资料");
        return Mono.just(user) ;
    }
}
