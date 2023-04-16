package com.yicj.hello.repository.impl;

import com.yicj.hello.entity.Book;
import com.yicj.hello.repository.BookRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yicj
 * @date: 2023/4/16 20:20
 */
@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> books = new ConcurrentHashMap<>() ;

    @Override
    public Flux<Book> findAll() {
        return Flux.fromIterable(books.values());
    }

    @Override
    public Mono<Book> findById(String id) {
        return Mono.justOrEmpty(books.get(id));
    }

    @Override
    public Mono<Book> save(Book book) {
        books.put(book.getId(), book) ;
        return Mono.just(book);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        books.remove(id);
        return Mono.empty();
    }
}
