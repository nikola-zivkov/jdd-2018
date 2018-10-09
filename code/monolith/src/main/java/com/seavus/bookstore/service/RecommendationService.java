/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.service;

import com.seavus.bookstore.dataaccess.BookRepository;
import com.seavus.bookstore.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendationService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> recommendSimilarBooks(String uuid) {
        Book subject = bookRepository.findById(uuid).get();
        return bookRepository.findAll()
                .stream()
                .filter(candidateIsNotSubject(subject))
                .filter(candidateHasRating())
                .filter(candidateIsOfSameGenreAsSubject(subject))
                .sorted((candidate1, candidate2) -> candidate2.getRating() - candidate1.getRating())
                .collect(Collectors.toList());
    }

    private Predicate<Book> candidateIsNotSubject(Book subject) {
        return candidate -> !candidate.getUuid().equals(subject.getUuid());
    }

    private Predicate<Book> candidateHasRating() {
        return candidate -> candidate.getRating() != null;
    }

    private Predicate<Book> candidateIsOfSameGenreAsSubject(Book subject) {
        return candidate -> candidate.getGenre().equals(subject.getGenre());
    }
}
