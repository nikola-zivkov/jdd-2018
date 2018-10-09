/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendationServiceAdapter implements RecommendationService {

    private final com.seavus.bookstore.recommendation.RecommendationService recommendationService;

    @Override
    public List<Product> recommendSimilarProducts(String uuid) {
        return recommendationService.recommendSimilarProducts(uuid)
                .stream()
                .map(ProductMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }
}
