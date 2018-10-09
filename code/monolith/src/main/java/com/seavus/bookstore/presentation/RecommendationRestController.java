/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.presentation;

import com.seavus.bookstore.domain.Book;
import com.seavus.bookstore.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class RecommendationRestController {

    private final RecommendationService recommendationService;

    @GetMapping("/{uuid}/similar")
    public List<Book> recommendSimilarBooks(@PathVariable String uuid) {
        return recommendationService.recommendSimilarBooks(uuid);
    }
}
