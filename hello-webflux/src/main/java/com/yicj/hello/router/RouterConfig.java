package com.yicj.hello.router;

import com.yicj.hello.handler.ApiHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * @author: yicj
 * @date: 2023/4/23 16:55
 */
public class RouterConfig {

    public static RouterFunction<ServerResponse> apiRoute(){
        ApiHandler handler = new ApiHandler() ;
        return RouterFunctions.route(GET("/api/prices"), handler::getPrices) ;
    }

}
