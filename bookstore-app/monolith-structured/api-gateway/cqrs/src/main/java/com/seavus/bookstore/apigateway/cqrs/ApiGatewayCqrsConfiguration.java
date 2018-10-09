/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.bookcatalog.BookQueryService;
import com.seavus.bookstore.recommendation.RecommendationQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApiGatewayCqrsConfiguration {

    @Bean("queryBookQueryService")
    BookQueryService queryBookQueryService(QBookRepository qBookRepository) {
        return new BookQueryService(qBookRepository);
    }

    @Bean("queryRecommendationQueryService")
    RecommendationQueryService queryRecommendationQueryService(QBookRepository qBookRepository) {
        return new RecommendationQueryService(qBookRepository);
    }
}
