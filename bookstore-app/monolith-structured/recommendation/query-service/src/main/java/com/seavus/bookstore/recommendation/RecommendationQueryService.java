/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RecommendationQueryService<T extends RecommendationQueryService.Product> {

    private final ProductRepository<T> productRepository;

    public List<T> recommendSimilarProducts(String uuid) {
        Product subject = productRepository.findByUuid(uuid);
        return productRepository.findAll()
                .stream()
                .filter(candidateIsNotSubject(subject))
                .filter(candidateHasRating())
                .filter(candidateIsOfSameCategoryAsSubject(subject))
                .sorted((candidate1, candidate2) -> candidate2.getRating() - candidate1.getRating())
                .collect(Collectors.toList());
    }

    private Predicate<Product> candidateIsNotSubject(Product subject) {
        return candidate -> !candidate.getUuid().equals(subject.getUuid());
    }

    private Predicate<Product> candidateHasRating() {
        return candidate -> candidate.getRating() != null;
    }

    private Predicate<Product> candidateIsOfSameCategoryAsSubject(Product subject) {
        return candidate -> candidate.getCategory().equals(subject.getCategory());
    }

    public static interface Product {

        String getUuid();

        Integer getRating();

        String getCategory();
    }

    public static interface ProductRepository<T extends Product> {

        T findByUuid(String uuid);

        List<T> findAll();
    }
}
