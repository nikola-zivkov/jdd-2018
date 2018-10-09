/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.service;

import com.google.common.collect.ImmutableList;
import com.seavus.bookstore.dataaccess.BookRepository;
import com.seavus.bookstore.domain.Book;
import com.seavus.bookstore.domain.Genre;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RecommendationServiceRecommendSimilarTest {

    private RecommendationService recommendationService;

    private BookRepository bookRepository;

    private Book subject;

    @Before
    public void setUp() throws Exception {
        subject = createBook("1", Genre.HORROR, Integer.MIN_VALUE);

        bookRepository = Mockito.mock(BookRepository.class);
        given(bookRepository.findById(subject.getUuid())).willReturn(Optional.of(subject));

        recommendationService = new RecommendationService(bookRepository);
    }

    @Test
    public void subjectIsExcluded() throws Exception {
        Book similar = createBook("2", Genre.HORROR, Integer.MIN_VALUE);
        given(bookRepository.findAll()).willReturn(ImmutableList.of(subject, similar));

        List<Book> recommendedSimilar = recommendationService.recommendSimilarBooks("1");

        assertThat(recommendedSimilar).extracting("uuid").containsExactly("2");
    }

    @Test
    public void onlyCandidatesOfSameGenreAreConsidered() throws Exception {
        Book similar = createBook("2", Genre.HORROR, Integer.MIN_VALUE);
        Book ofDifferentGenre = createBook("3", Genre.SF, Integer.MIN_VALUE);
        given(bookRepository.findAll()).willReturn(ImmutableList.of(subject, similar, ofDifferentGenre));

        List<Book> recommendedSimilar = recommendationService.recommendSimilarBooks("1");

        assertThat(recommendedSimilar).extracting("uuid").containsExactly("2");
    }

    @Test
    public void recommendedSimilarAreOrderedByRating() throws Exception {
        Book similarWithLowerRating = createBook("2", Genre.HORROR, 1);
        Book similarWithHigherRating = createBook("3", Genre.HORROR, 2);
        given(bookRepository.findAll()).willReturn(ImmutableList.of(subject,
                similarWithLowerRating,
                similarWithHigherRating));

        List<Book> recommendedSimilar = recommendationService.recommendSimilarBooks("1");

        assertThat(recommendedSimilar).extracting("uuid").containsExactly("3", "2");
    }

    @Test
    public void notRatedAreExcluded() throws Exception {
        Book notRated = createBook("2", Genre.HORROR, null);
        given(bookRepository.findAll()).willReturn(ImmutableList.of(subject, notRated));

        List<Book> recommendedSimilar = recommendationService.recommendSimilarBooks("1");

        assertThat(recommendedSimilar).isEmpty();
    }

    private Book createBook(String uuid, Genre genre, Integer rating) {
        Book book = new Book(null, null, genre);
        book.setUuid(uuid);
        book.setRating(rating);
        return book;
    }
}