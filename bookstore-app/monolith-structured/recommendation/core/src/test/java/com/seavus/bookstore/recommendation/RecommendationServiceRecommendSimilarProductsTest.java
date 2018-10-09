/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RecommendationServiceRecommendSimilarProductsTest {

    private static final String GENRE_HORROR = "HORROR";

    private static final String GENRE_SF = "SF";

    private RecommendationService recommendationService;

    private ProductRepository productRepository;

    private Product subject;

    @Before
    public void setUp() throws Exception {
        subject = createProduct("1", GENRE_HORROR, Integer.MIN_VALUE);

        productRepository = Mockito.mock(ProductRepository.class);
        given(productRepository.findByUuid(subject.getUuid())).willReturn(subject);

        recommendationService = new RecommendationService(new RecommendationQueryService<>(productRepository));
    }

    @Test
    public void subjectIsExcluded() throws Exception {
        Product similar = createProduct("2", GENRE_HORROR, Integer.MIN_VALUE);
        given(productRepository.findAll()).willReturn(ImmutableList.of(subject, similar));

        List<Product> recommendedSimilar = recommendationService.recommendSimilarProducts("1");

        assertThat(recommendedSimilar).extracting("uuid").containsExactly("2");
    }

    @Test
    public void onlyCandidatesOfSameGenreAreConsidered() throws Exception {
        Product similar = createProduct("2", GENRE_HORROR, Integer.MIN_VALUE);
        Product ofDifferentGenre = createProduct("3", GENRE_SF, Integer.MIN_VALUE);
        given(productRepository.findAll()).willReturn(ImmutableList.of(subject, similar, ofDifferentGenre));

        List<Product> recommendedSimilar = recommendationService.recommendSimilarProducts("1");

        assertThat(recommendedSimilar).extracting("uuid").containsExactly("2");
    }

    @Test
    public void recommendedSimilarAreOrderedByRating() throws Exception {
        Product similarWithLowerRating = createProduct("2", GENRE_HORROR, 1);
        Product similarWithHigherRating = createProduct("3", GENRE_HORROR, 2);
        given(productRepository.findAll()).willReturn(ImmutableList.of(subject,
                similarWithLowerRating,
                similarWithHigherRating));

        List<Product> recommendedSimilar = recommendationService.recommendSimilarProducts("1");

        assertThat(recommendedSimilar).extracting("uuid").containsExactly("3", "2");
    }

    @Test
    public void notRatedAreExcluded() throws Exception {
        Product notRated = createProduct("2", GENRE_HORROR, null);
        given(productRepository.findAll()).willReturn(ImmutableList.of(subject, notRated));

        List<Product> recommendedSimilar = recommendationService.recommendSimilarProducts("1");

        assertThat(recommendedSimilar).isEmpty();
    }

    private Product createProduct(String uuid, String genre, Integer rating) {
        Product product = new Product(genre);
        product.setUuid(uuid);
        product.setRating(rating);
        return product;
    }
}