/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recommendation/api/products")
public class RecommendationServiceRestController implements RecommendationService {

    private final RecommendationServiceAdapter recommendationService;

    @Override
    @GetMapping("/{uuid}/similar")
    public List<Product> recommendSimilarProducts(@PathVariable("uuid") String uuid) {
        return recommendationService.recommendSimilarProducts(uuid);
    }
}
