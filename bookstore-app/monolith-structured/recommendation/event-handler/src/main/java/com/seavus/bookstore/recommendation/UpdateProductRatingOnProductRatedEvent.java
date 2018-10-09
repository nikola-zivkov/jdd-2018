/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import com.seavus.bookstore.rating.ProductRatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class UpdateProductRatingOnProductRatedEvent {

    private final ProductService productService;

    @StreamListener("com_seavus_bookstore_recommendation_UpdateProductRatingOnProductRatedEvent")
    void notify(ProductRatedEvent productRatedEvent) {
        productService.updateProductRating(productRatedEvent.getUuid(), productRatedEvent.getRating());
    }
}
