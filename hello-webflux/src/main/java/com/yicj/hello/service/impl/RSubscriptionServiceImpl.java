package com.yicj.hello.service.impl;

import com.yicj.hello.service.RSubscriptionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author: yicj
 * @date: 2023/4/23 14:41
 */
@Service
public class RSubscriptionServiceImpl implements RSubscriptionService {

    @Override
    public Mono<Void> addSubscription(String name, String symbol) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mono.fromSupplier(() -> "hello").then();
    }
}
