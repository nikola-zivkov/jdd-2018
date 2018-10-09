/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.rating.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingServiceAdapter implements RatingService {

    private final com.seavus.bookstore.rating.RatingService ratingService;

    @Override
    public void rateProduct(String uuid, Integer rating) {
        ratingService.rateProduct(uuid, rating);
    }

    @Override
    public Integer getProductRating(String uuid) {
        return ratingService.getProductRating(uuid);
    }
}
