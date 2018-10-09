/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.bookcatalog.BookQueryService;
import com.seavus.bookstore.recommendation.RecommendationQueryService;
import org.springframework.data.jpa.repository.JpaRepository;

interface QBookRepository
        extends BookQueryService.BookRepository<QBook>, RecommendationQueryService.ProductRepository<QBook>,
        JpaRepository<QBook, Long> {

    QBook findByUuid(String uuid);
}
