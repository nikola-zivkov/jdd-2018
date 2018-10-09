/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PosterResourceProxyGateway {

    private final DiscoveryClient discoveryClient;

    @GetMapping("/poster/{name}")
    public Object getPoster(ProxyExchange<Object> proxy, @PathVariable("name") String name) {
        return proxy.uri(getServiceUri() + "/poster/" + name.replaceAll(" ", "%20")).get();
    }

    private String getServiceUri() {
        return discoveryClient.getInstances("static-resources").get(0).getUri().toString();
    }
}
