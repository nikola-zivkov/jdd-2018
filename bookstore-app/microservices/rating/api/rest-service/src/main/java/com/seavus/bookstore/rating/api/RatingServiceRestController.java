/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.rating.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rating/api/products")
public class RatingServiceRestController implements RatingService {

    private final RatingServiceAdapter ratingService;

    @Override
    public void rateProduct(@PathVariable("uuid") String uuid, Integer rating) {
        ratingService.rateProduct(uuid, rating);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateProduct(@PathVariable("uuid") String uuid, @RequestBody RateBookDto request) {
        this.rateProduct(uuid, request.rating);
        ratingService.rateProduct(uuid, request.rating);
    }

    @Override
    @GetMapping("/{uuid}/rating")
    public Integer getProductRating(@PathVariable("uuid") String uuid) {
        return ratingService.getProductRating(uuid);
    }

    private static class RateBookDto {

        Integer rating;
    }
}
