/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.composer;

import com.seavus.bookstore.bookcatalog.api.BookService;
import com.seavus.bookstore.rating.api.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/books")
@Profile("api-gateway.composer")
public class BookRestController {

    private final BookService bookService;

    private final RatingService ratingService;

    @GetMapping
    public List<Book> findBooks() {
        List<Book> books = bookService.findBooks()
                .stream()
                .map(BookMapper.INSTANCE::map)
                .peek(setRating())
                .collect(Collectors.toList());
        return books;
    }

    private Consumer<Book> setRating() {
        return book -> {
            try {
                book.setRating(ratingService.getProductRating(book.getUuid()));
            } catch (RuntimeException e) {
                log.warn("Unable to get rating for book {}", book.getUuid());
            }
        };
    }
}
