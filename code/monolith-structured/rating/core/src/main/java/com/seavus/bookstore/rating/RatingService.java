/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.rating;

import com.seavus.bookstore.messaging.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final ProductRepository productRepository;

    private final MessagePublisher messagePublisher;

    @Transactional
    public void rateProduct(String uuid, Integer rating) {
        Product product = productRepository.findById(uuid).get();
        product.setRating(rating);
        productRepository.save(product);
        messagePublisher.publish(new ProductRatedEvent(product.getUuid(), product.getRating()));
    }

    @Transactional(readOnly = true)
    public Integer getProductRating(String uuid) {
        return productRepository.findById(uuid).get().getRating();
    }
}
