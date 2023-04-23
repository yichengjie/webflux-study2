package com.yicj.hello.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: yicj
 * @date: 2023/4/23 16:55
 */
public class ApiHandler {

    public Mono<ServerResponse> getPrices(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("test"), String.class) ;
    }
}
