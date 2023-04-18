package com.yicj.hello.handler;

import com.yicj.hello.entity.Book;
import com.yicj.hello.repository.BookRepository;
import com.yicj.hello.utils.CommonUtils;
import com.yicj.hello.vo.UserIdentity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author: yicj
 * @date: 2023/4/16 20:23
 */
@Slf4j
@Component
public class BookHandler {

    private final BookRepository bookRepository ;

    public BookHandler(BookRepository bookRepository){
        this.bookRepository = bookRepository ;
    }

    public Mono<ServerResponse> getAllBooks(ServerRequest request){
        return ServerResponse.ok().body(bookRepository.findAll(), Book.class) ;
    }

    public Mono<ServerResponse> getBookById(ServerRequest request){
        UserIdentity userIdentity = CommonUtils.parseUserIdentity(request);
        log.info("user identity : {}", userIdentity);
        if (userIdentity == null){
            return ServerResponse.noContent().build();
        }
        return bookRepository.findById(request.pathVariable("id"))
                .flatMap(book -> ServerResponse.ok().body(BodyInserters.fromValue(book)))
                .switchIfEmpty(ServerResponse.notFound().build()) ;
    }

    public Mono<ServerResponse> saveBook(ServerRequest request){
        Mono<Book> bookMono = request.bodyToMono(Book.class);
        return bookMono.flatMap(book ->
            ServerResponse.status(HttpStatus.CREATED).body(bookRepository.save(book), Book.class)
        ) ;
    }

    public Mono<ServerResponse> deleteBookById(ServerRequest request){
        return bookRepository.deleteById(request.pathVariable("id"))
                .then(ServerResponse.noContent().build()) ;
    }
}
