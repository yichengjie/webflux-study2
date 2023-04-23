package com.yicj.hello.controller;

import com.yicj.hello.model.vo.UserVO;
import com.yicj.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @PostMapping("/save")
    public UserVO save(@RequestBody UserVO userVO){
        return userService.save(userVO) ;
    }
}
