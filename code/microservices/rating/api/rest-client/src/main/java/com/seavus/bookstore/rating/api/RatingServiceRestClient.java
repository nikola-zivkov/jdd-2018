/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.rating.api;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class RatingServiceRestClient implements RatingService {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void rateProduct(String uuid, Integer rating) {
        restTemplate.put(getServiceUri() + "/rating/api/products/{uuid}",
                new HttpEntity<Map>(ImmutableMap.of("rating", rating)),
                uuid);
    }

    @Override
    public Integer getProductRating(String uuid) {
        return restTemplate.getForObject(getServiceUri() + "/rating/api/products/{uuid}/rating", Integer.class, uuid);
    }

    private String getServiceUri() {
        return discoveryClient.getInstances("rating").get(0).getUri().toString();
    }
}
