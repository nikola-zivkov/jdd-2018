/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.rating.ProductRatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateQBookRatingOnProductRatedEvent {

    private final QBookRepository qBookRepository;

    @StreamListener("com_seavus_bookstore_api_cqrs_UpdateQBookRatingOnProductRatedEvent")
    public void notify(ProductRatedEvent productRatedEvent) {
        QBook qBook = qBookRepository.findByUuid(productRatedEvent.getUuid());
        qBook.setRating(productRatedEvent.getRating());
        qBookRepository.save(qBook);
    }
}
