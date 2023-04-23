package com.yicj.hello.service.impl;

import com.yicj.hello.entity.StockSubscription;
import com.yicj.hello.service.RSubscriptionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    @Override
    public Flux<StockSubscription> findByEmail(String email) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Flux.generate(sink -> {
            StockSubscription subscription = new StockSubscription();
            subscription.setSymbol("test symbol");
            subscription.setEmail(email);
            sink.next(subscription);
            sink.complete();
        });
    }
}
