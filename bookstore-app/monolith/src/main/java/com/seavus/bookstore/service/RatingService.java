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

@RequiredArgsConstructor
@Service
public class RatingService {

    private final BookRepository bookRepository;

    @Transactional
    public void rateBook(String uuid, Integer rating) {
        Book book = bookRepository.findById(uuid).get();
        book.setRating(rating);
        bookRepository.save(book);
    }
}
