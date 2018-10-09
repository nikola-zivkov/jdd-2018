/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookQueryService<T extends BookQueryService.Book> {

    private final BookRepository<T> bookRepository;

    public T findByUuid(String uuid) {
        return bookRepository.findByUuid(uuid);
    }

    public List<T> findBooks() {
        return bookRepository.findAll();
    }

    public static interface Book {}

    public static interface BookRepository<T extends Book> {

        T findByUuid(String uuid);

        List<T> findAll();
    }
}
