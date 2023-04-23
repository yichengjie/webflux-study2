package com.yicj.hello.controller;

import com.yicj.hello.model.vo.UserVO;
import com.yicj.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yicj
 * @date: 2023/4/16 21:38
 */
@RestController
@RequestMapping("/webmvc")
public class HelloController {

    @Autowired
    private UserService userService ;

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

    @GetMapping("/user/save")
    public UserVO saveAB(){
        UserVO vo = new UserVO() ;
        vo.setName("李四");
        vo.setAddress("BJS");
        return userService.save(vo) ;
    }
}
