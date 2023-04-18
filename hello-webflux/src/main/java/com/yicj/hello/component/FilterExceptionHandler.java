package com.yicj.hello.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yicj.hello.exception.AppException;
import com.yicj.hello.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;

import java.nio.charset.StandardCharsets;

/**
 * @author yicj
 * @date 2023年04月18日 16:35
 */
//@Slf4j
//@Order(-1)
//@Component
//public class FilterExceptionHandler implements ErrorWebExceptionHandler {
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        log.error("==> 捕获异常: ", ex);
//        // 这个有个问题，当找不到地址时候也会报系统异常
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.OK) ;
//        ResultVO<String> vo ;
//        if (ex instanceof AppException){
//            AppException appException = (AppException) ex;
//            vo = ResultVO.fail(appException.getCode(), ex.getMessage());
//        }else if (ex instanceof ResponseStatusException){
////            ResponseStatusException statusException = (ResponseStatusException)ex ;
////            HttpStatus status = statusException.getStatus();
//            vo = ResultVO.fail(ex.getMessage());
//        }else {
//            vo = ResultVO.fail("系统异常");
//        }
//        DataBuffer dataBuffer = response.bufferFactory()
//                .allocateBuffer()
//                .write(JSON.toJSONString(vo).getBytes(StandardCharsets.UTF_8));
//        // 基于流形式
//        response.getHeaders().setContentType(MediaType.APPLICATION_STREAM_JSON);
//        return response.writeAndFlushWith(Mono.just(ByteBufMono.just(dataBuffer)));
//    }
//}
