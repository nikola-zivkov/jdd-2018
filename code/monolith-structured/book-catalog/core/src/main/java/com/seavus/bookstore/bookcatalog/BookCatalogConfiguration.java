/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog;

import com.seavus.bookstore.bookcatalog.BookQueryService;
import com.seavus.bookstore.bookcatalog.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BookCatalogConfiguration {

    @Bean("coreBookQueryService")
    BookQueryService coreBookQueryService(BookRepository bookRepository) {
        return new BookQueryService(bookRepository);
    }
}
