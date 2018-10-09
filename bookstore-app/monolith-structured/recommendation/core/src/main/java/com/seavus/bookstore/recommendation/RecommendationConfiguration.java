/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import com.seavus.bookstore.recommendation.ProductRepository;
import com.seavus.bookstore.recommendation.RecommendationQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RecommendationConfiguration {

    @Bean("coreRecommendationQueryService")
    RecommendationQueryService coreRecommendationQueryService(ProductRepository productRepository) {
        return new RecommendationQueryService(productRepository);
    }
}
