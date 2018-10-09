/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.recommendation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RecommendationServiceRestClient implements RecommendationService {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Product> recommendSimilarProducts(String uuid) {
        return Arrays.asList(restTemplate.getForObject(getServiceUri() + "/recommendation/api/products/{uuid}/similar",
                Product[].class,
                uuid));
    }

    private String getServiceUri() {
        return discoveryClient.getInstances("recommendation").get(0).getUri().toString();
    }
}
