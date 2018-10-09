/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.composer;

import com.seavus.bookstore.bookcatalog.api.BookService;
import com.seavus.bookstore.recommendation.api.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class RecommendationRestController {

    private final RecommendationService recommendationService;

    private final BookService bookService;

    @GetMapping("/{uuid}/similar")
    public List<Book> recommendSimilarBooks(@PathVariable("uuid") String uuid) {
        try {
            List<Book> similarBooks = recommendationService.recommendSimilarProducts(uuid)
                    .stream()
                    .map(BookMapper.INSTANCE::map)
                    .peek(setTitleAuthorAndGenre())
                    .collect(Collectors.toList());
            return similarBooks;

        } catch (RuntimeException e) {
            log.warn("Unable to recommend books similar to {}", uuid);
            return null;
        }
    }

    private Consumer<Book> setTitleAuthorAndGenre() {
        return book -> {
            com.seavus.bookstore.bookcatalog.api.Book bookManagementBook = bookService.findBook(book.getUuid());
            book.setTitle(bookManagementBook.getTitle());
            book.setAuthor(bookManagementBook.getAuthor());
            book.setGenre(bookManagementBook.getGenre());
        };
    }
}
