/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book-catalog/api/books")
public class BookServiceRestController implements BookService {

    private final BookServiceAdapter bookService;

    @Override
    @GetMapping("/{uuid}")
    public Book findBook(@PathVariable("uuid") String uuid) {
        return bookService.findBook(uuid);
    }

    @Override
    @GetMapping
    public List<Book> findBooks() {
        return bookService.findBooks();
    }
}
