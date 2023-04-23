package com.yicj.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

//    @Bean
//    public WebClient webClient(){
//        HttpClient httpClient = HttpClient.create() ;
//        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient) ;
//        return WebClient.builder().clientConnector(connector).build() ;
//    }

    @Bean
    public WebClient webClient(){
        //maxConnections：允许的最大连接数
        //pendingAcquireTimeout：没有连接可用时，请求等待的最长时间
        //maxIdleTime：连接最大闲置时间
        ConnectionProvider provider = ConnectionProvider.builder("order")
                .maxConnections(100)
                .maxIdleTime(Duration.ofMillis(100))
                .pendingAcquireTimeout(Duration.ofMillis(100))
                .build() ;
        // 底层使用netty时，可以如下配置超时时间
        HttpClient httpClient = HttpClient.create(provider)
                .responseTimeout(Duration.ofSeconds(2)) ;
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build() ;
    }

//    @Bean
//    @LoadBalanced
//    public WebClient.Builder loadBalancedWebClientBuilder() {
//        return WebClient.builder();
//    }
}
