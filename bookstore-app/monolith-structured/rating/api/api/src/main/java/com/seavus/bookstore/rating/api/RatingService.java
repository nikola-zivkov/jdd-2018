/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.rating.api;

public interface RatingService {

    void rateProduct(String uuid, Integer rating);

    Integer getProductRating(String uuid);
}
