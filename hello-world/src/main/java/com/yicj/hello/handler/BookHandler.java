package com.yicj.hello.handler;

import com.yicj.hello.entity.Book;
import com.yicj.hello.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: yicj
 * @date: 2023/4/16 20:23
 */
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
