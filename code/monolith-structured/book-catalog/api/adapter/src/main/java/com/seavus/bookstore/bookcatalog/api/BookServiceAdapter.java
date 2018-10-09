/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceAdapter implements BookService {

    private final com.seavus.bookstore.bookcatalog.BookService bookService;

    @Override
    public Book findBook(String uuid) {
        return BookMapper.INSTANCE.map(bookService.findBook(uuid));
    }

    @Override
    public List<Book> findBooks() {
        return bookService.findBooks().stream().map(BookMapper.INSTANCE::map).collect(Collectors.toList());
    }
}
