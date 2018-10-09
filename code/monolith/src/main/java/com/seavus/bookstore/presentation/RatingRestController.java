/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.presentation;

import com.seavus.bookstore.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class RatingRestController {

    private final RatingService ratingService;

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateBook(@PathVariable String uuid, @RequestBody RateBookDto request) {
        ratingService.rateBook(uuid, request.rating);
    }

    private static class RateBookDto {
        Integer rating;
    }
}
