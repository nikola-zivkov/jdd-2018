/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("com.seavus.bookstore.recommendation.ProductService")
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void updateProductRating(String uuid, Integer rating) {
        Product product = productRepository.findByUuid(uuid);
        product.setRating(rating);
        productRepository.save(product);
    }
}
