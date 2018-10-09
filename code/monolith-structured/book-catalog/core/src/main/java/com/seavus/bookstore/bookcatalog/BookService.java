/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog;

import com.seavus.bookstore.messaging.MessagePublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookQueryService<Book> bookQueryService;

    private final MessagePublisher messagePublisher;

    public BookService(BookRepository bookRepository,
            @Qualifier("coreBookQueryService") BookQueryService bookQueryService, MessagePublisher messagePublisher) {
        this.bookRepository = bookRepository;
        this.bookQueryService = bookQueryService;
        this.messagePublisher = messagePublisher;
    }

    @Transactional
    public Book createBook(String title, String author, Genre genre) {
        Book book = bookRepository.save(new Book(title, author, genre));
        messagePublisher.publish(new BookCreatedEvent(book.getUuid(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre().toString()));
        return book;
    }

    @Transactional(readOnly = true)
    public Book findBook(String uuid) {
        return bookQueryService.findByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public List<Book> findBooks() {
        return bookQueryService.findBooks();
    }
}
