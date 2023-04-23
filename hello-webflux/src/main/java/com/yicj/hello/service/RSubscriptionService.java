package com.yicj.hello.service;

import reactor.core.publisher.Mono;

/**
 * @author: yicj
 * @date: 2023/4/23 14:38
 */
public interface RSubscriptionService {

    Mono<Void> addSubscription(String name, String symbol) ;
}
