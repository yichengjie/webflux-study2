package com.yicj.hello.router;

import com.yicj.hello.entity.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import java.util.Arrays;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author: yicj
 * @date: 2023/4/16 19:42
 */
@Configuration
public class MessageRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/"), request -> ServerResponse.ok().body(BodyInserters.fromObject(Arrays.asList(
                Message.builder().body("hello Spring 5").build(),
                Message.builder().body("hello Spring Boot 2").build()
        )))) ;
    }
}
