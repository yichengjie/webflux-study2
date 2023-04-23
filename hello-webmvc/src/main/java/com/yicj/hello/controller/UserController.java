package com.yicj.hello.controller;

import com.yicj.hello.model.vo.UserVO;
import com.yicj.hello.utils.CommonUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/save")
    public UserVO save(@RequestBody UserVO userVO){
        String uuid = CommonUtils.uuid() ;
        userVO.setId(uuid);
        return userVO ;
    }
}
