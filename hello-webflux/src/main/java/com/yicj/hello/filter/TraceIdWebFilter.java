package com.yicj.hello.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author yicj
 * @date 2023年04月18日 17:57
 */
//@Slf4j
//@Component
//public class TraceIdWebFilter implements WebFilter {
//
//    private static final String TRACE_ID = "trace-id";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
////        chain.filter(exchange)
////                // 放入当前上下文，类似ThreadLocal
////                .contextWrite()
////        Mono<String> r = Mono
////                .deferContextual(ctx -> Mono.just("Hello " + ctx.get(key)))
////                .contextWrite(ctx -> ctx.put(key, "Reactor"))
////                .flatMap( s -> Mono.deferContextual(ctx ->
////                        Mono.just(s + " " + ctx.get(key))))
////                .contextWrite(ctx -> ctx.put(key, "World"));
//        return null;
//    }
//}
