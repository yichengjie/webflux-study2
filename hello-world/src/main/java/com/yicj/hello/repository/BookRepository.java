package com.yicj.hello.repository;

import com.yicj.hello.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: yicj
 * @date: 2023/4/16 20:18
 */
public interface BookRepository {

    Flux<Book> findAll() ;

    Mono<Book> findById(String id) ;

    Mono<Book> save(Book book) ;

    Mono<Void> deleteById(String id) ;
}
