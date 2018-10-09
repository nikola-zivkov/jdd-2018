/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog.api;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookServiceRestClient implements BookService {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Book findBook(String uuid) {
        return restTemplate.getForObject(getServiceUri() + "/book-catalog/api/books/{uuid}", Book.class, uuid);
    }

    @Override
    public List<Book> findBooks() {
        return Arrays.asList(restTemplate.getForObject(getServiceUri() + "/book-catalog/api/books", Book[].class));
    }

    private String getServiceUri() {
        return discoveryClient.getInstances("book-catalog").get(0).getUri().toString();
    }
}
