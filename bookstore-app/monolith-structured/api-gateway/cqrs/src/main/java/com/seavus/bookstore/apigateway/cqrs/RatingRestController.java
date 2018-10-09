/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.rating.api.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
@Profile("api-gateway.cqrs")
public class RatingRestController {

    private final RatingService ratingService;

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateBook(@PathVariable("uuid") String uuid, @RequestBody RateBookDto request) {
        ratingService.rateProduct(uuid, request.rating);
    }

    private static class RateBookDto {
        Integer rating;
    }
}
