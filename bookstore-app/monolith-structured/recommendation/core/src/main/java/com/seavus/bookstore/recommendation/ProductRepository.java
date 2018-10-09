/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("com.seavus.bookstore.recommendation.ProductRepository")
interface ProductRepository
        extends RecommendationQueryService.ProductRepository<Product>, JpaRepository<Product, String> {
}
