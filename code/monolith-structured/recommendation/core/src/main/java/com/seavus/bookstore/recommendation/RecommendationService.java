/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationQueryService<Product> recommendationQueryService;

    public RecommendationService(
            @Qualifier("coreRecommendationQueryService") RecommendationQueryService<Product>
                    recommendationQueryService) {
        this.recommendationQueryService = recommendationQueryService;
    }

    @Transactional(readOnly = true)
    public List<Product> recommendSimilarProducts(String uuid) {
        return recommendationQueryService.recommendSimilarProducts(uuid);
    }
}
