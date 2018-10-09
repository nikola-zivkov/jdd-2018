/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepository extends BookQueryService.BookRepository<Book>, JpaRepository<Book, Long> {

    Book findByUuid(String uuid);
}
