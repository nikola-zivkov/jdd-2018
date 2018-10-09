/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.recommendation.RecommendationQueryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Profile("api-gateway.cqrs")
public class RecommendationRestController {

    public RecommendationRestController(
            @Qualifier("queryRecommendationQueryService") RecommendationQueryService<QBook>
                    recommendationQueryService) {
        this.recommendationQueryService = recommendationQueryService;
    }

    private final RecommendationQueryService<QBook> recommendationQueryService;

    @GetMapping("/{uuid}/similar")
    public List<Book> recommendSimilarBooks(@PathVariable("uuid") String uuid) {
        List<Book> similarBooks = recommendationQueryService.recommendSimilarProducts(uuid)
                .stream()
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
        return similarBooks;
    }
}
