/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.bookcatalog.BookQueryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Profile("api-gateway.cqrs")
public class BookRestController {

    private final BookQueryService<QBook> bookQueryService;

    public BookRestController(@Qualifier("queryBookQueryService") BookQueryService bookQueryService) {
        this.bookQueryService = bookQueryService;
    }

    @GetMapping
    public List<Book> findBooks() {
        List<Book> books = bookQueryService.findBooks()
                .stream()
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
        return books;
    }
}
