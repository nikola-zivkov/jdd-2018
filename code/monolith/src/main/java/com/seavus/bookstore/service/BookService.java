/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.service;

import com.seavus.bookstore.dataaccess.BookRepository;
import com.seavus.bookstore.domain.Book;
import com.seavus.bookstore.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book createBook(String title, String author, Genre genre) {
        return bookRepository.save(new Book(title, author, genre));
    }

    @Transactional(readOnly = true)
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }
}
