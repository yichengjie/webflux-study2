package com.yicj.hello.controller;

import com.yicj.hello.exception.AppException;
import com.yicj.hello.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author yicj
 * @date 2023年04月18日 16:48
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    List<UserVO> userList = Arrays.asList(
            new UserVO("张三", "开发人员"),
            new UserVO("李四", "开发人员"),
            new UserVO("王五", "测试人员")
    );

    @GetMapping("/listAll")
    public Flux<UserVO> listAll(){
        return Flux.fromIterable(userList)
                .concatMap(user -> Mono.just(new UserVO(user.getName(), user.getName()))) ;
    }


    @GetMapping("/findByName/{name}")
    public Mono<UserVO> findByName(@PathVariable("name") String name){
        if ("李四1".equals(name)){
            throw new RuntimeException("李四1为非法用户，不支持搜索！");
        }
        return Flux.fromIterable(userList)
                .concatMap(user -> Mono.just(new UserVO(user.getName(), user.getDesc())))
                .filter(user -> user.getName().equals(name))
                .next()
                .switchIfEmpty(Mono.error(new AppException("4001", "未查询到用户 : " + name))) ;
    }

}
