package com.yicj.hello.remote.webmvc;

import com.yicj.hello.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class HelloRpc {

    @Autowired
    private WebClient webClient ;

    public Mono<UserVO> save(String name, String address){
        UserVO vo = new UserVO() ;
        vo.setName(name);
        vo.setAddress(address);
        return webClient.post()
                .uri("http://localhost:8081/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(vo), UserVO.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)){
                        log.info("status : {}", clientResponse.statusCode());
                        return clientResponse.bodyToMono(UserVO.class) ;
                    }
                    return clientResponse.createException().flatMap(Mono::error) ;
                }) ;
    }

}
