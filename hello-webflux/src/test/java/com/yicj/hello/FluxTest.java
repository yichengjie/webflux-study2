package com.yicj.hello;

import com.yicj.hello.entity.User;
import com.yicj.hello.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.util.StringUtils;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author yicj
 * @date 2023年04月18日 14:19
 */
@Slf4j
public class FluxTest {

    @Test
    public void hello(){
        List<User> list = Arrays.asList(
                new User("张三", "开发人员"),
                new User("李四", "开发人员"),
                new User("王五", "测试人员"),
                new User("赵六", "产品经理")) ;
        List<String> retList = Flux.fromIterable(list)
                .concatMap(user -> Mono.just(new UserVO(user.getName(), user.getDesc())))
                //.next()
                .switchIfEmpty(Mono.error(new RuntimeException("not found exception")))
                .flatMap(vo -> Mono.just(vo.getName()))
                .collectList()
                .block();
        log.info("value : {}", retList);
    }

    @Test
    public void hello2(){
        try {
            // String userName = "张三" ;
            // String desc = "测试人员" ;
            String userName = null ;
            String desc = null ;
            Random random = new Random() ;
            long start = System.currentTimeMillis();
            List<User> list = Arrays.asList(
                    new User("张三", "开发人员"),
                    new User("李四", "开发人员"),
                    new User("王五", "测试人员"),
                    new User("赵六", "产品经理")) ;
            List<User> retList = Flux.fromIterable(list)
                    .concatMap(user -> {
                        int num = random.nextInt(100);
                        log.info("name: {}, num : {}", user.getName() ,num);
//                        if (user.getName().equals("王五")){
//                            throw new RuntimeException("王五处理报错") ;
//                        }
                        //return Mono.delay(Duration.ofMillis(num)).thenReturn(new User(user.getName(), user.getDesc())) ;
                        return Mono.delay(Duration.ofMillis(num)).then(Mono.just(user)) ;
                        //return Mono.just(new User(user.getName(), user.getDesc())).delayElement(Duration.ofMillis(num)) ;
                    })
                    .filter(item -> {
                        //log.info("filter1 : {}", item.getName());
                        if(!StringUtils.isEmpty(userName)){
                            return item.getName().equals("张三") ;
                        }
                        return true ;
                    })
                    .filter(item -> {
                        //log.info("filter2 : {}", item.getName());
                        if (!StringUtils.isEmpty(desc)){
                            return item.getDesc().equals(desc) ;
                        }
                        return true ;
                    })
                    //.next()
                    .switchIfEmpty(Mono.error(new RuntimeException("not found exception")))
                    .flatMap(vo -> Mono.just(new User(vo.getName(), vo.getDesc())))
                    .collectList().block();
            log.info("take time : {}", (System.currentTimeMillis() - start));
            assert retList != null;
            retList.forEach(item -> log.info("--> {}", item));
        }catch (Exception e){
            log.error("error : {}", e.getMessage(), e);
        }
    }

    @Test
    public void deffer() throws InterruptedException {
        Mono<String> just = Mono.just(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        Mono<String> defer = Mono.defer(() -> Mono.just(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        just.subscribe(System.out::println) ;
        defer.subscribe(System.out::println) ;
        Thread.sleep(2000);
        System.out.println("-------------------");
        just.subscribe(System.out::println) ;
        defer.subscribe(System.out::println) ;
        // just发出的元素是在实例化时就准备好的
        // defer在有订阅时才发出元素。
    }

    @Test
    public void subscribeWithErrorConsumerAndCompleteConsumer(){
        Flux.range(1,5)
            .map(i -> {
                if (i == 3){
                    throw new RuntimeException("i = 3 抛出异常") ;
                }
                return i ;
            })
            .subscribe(i -> log.info("value : {}", i),
                    err -> log.info("error : {}", err.getMessage(), err),
                    () -> log.info("subscription completed")) ;
        // errorConsumer and complete consumer are mutually exclusive
    }

    @Test
    public void subscribeWithSubscription(){
        Flux<Integer> ints = Flux.range(1,4) ;
        Consumer<? super Subscription> subscriptionConsumer1 = null ;
        Consumer<? super Subscription> subscriptionConsumer2 = sub -> sub.request(3) ;
        Consumer<? super Subscription> subscriptionConsumer3 = sub -> sub.request(5) ;
        Consumer<? super Subscription> subscriptionConsumer4 = sub -> sub.cancel() ; // 不发布任何数据
        Consumer<? super Subscription> subscriptionConsumer5 = sub -> sub.getClass() ; // no request

        ints.log()
            .subscribe(i -> {
                        log.info("i = {}", i) ;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    },
                    null,
                    () -> log.info("subscription completed"),
                    subscriptionConsumer1) ;


    }


    @Test
    public void subscribeWithBaseSubscriber(){
        Flux<Integer> ints = Flux.range(1,4) ;
        ints.subscribe(new SampleSubscriber<>()) ;
    }

    public class SampleSubscriber<T> extends BaseSubscriber<T>{
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            //super.hookOnSubscribe(subscription);
            log.info("subscribed");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            log.info("i = {}", value);
            request(1);
        }
    }

}
