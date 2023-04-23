package com.yicj.hello.controller;

import com.yicj.hello.entity.StockSymbol;
import com.yicj.hello.service.RSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author: yicj
 * @date: 2023/4/23 14:37
 */
@Slf4j
@RestController
public class SubscriptionController {

    @Autowired
    private RSubscriptionService subscriptionService ;

    @PostMapping
    public Mono<String> addSubscription(@ModelAttribute("stockSymbol")StockSymbol symbol){
        String name = "张三" ;
        return Mono.fromSupplier(() -> {
            subscriptionService.addSubscription(name, symbol.getSymbol())
                    //.subscribeOn(Schedulers.newSingle("addSubThread"))
                    .subscribe() ;
            return "redirect:/subscription?added=" + symbol.getSymbol() ;
        }) ;
    }

}
