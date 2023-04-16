package com.yicj.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yicj
 * @date: 2023/4/16 21:45
 */
@RestController
@RequestMapping("/webmvc")
public class HelloWebMvcController {

    @GetMapping("/hello")
    public String mvc(){

        return "Hello" ;
    }

    @GetMapping("/sleep/{time}")
    public String sleep(@PathVariable("time") int time){
        try {
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Sleep " + time + "ms, Current Time : " + System.currentTimeMillis() ;
    }

}
