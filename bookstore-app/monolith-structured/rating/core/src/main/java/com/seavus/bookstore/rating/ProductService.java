/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("com.seavus.bookstore.rating.ProductService")
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(String uuid) {
        Product product = new Product();
        product.setUuid(uuid);
        return productRepository.save(product);
    }
}
