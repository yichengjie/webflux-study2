package com.yicj.hello.service;

import com.yicj.hello.entity.StockSubscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: yicj
 * @date: 2023/4/23 14:38
 */
public interface RSubscriptionService {

    Mono<Void> addSubscription(String name, String symbol) ;

    Flux<StockSubscription> findByEmail(String email) ;
}
