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
public class ServiceStatusProxyGateway {

    private final DiscoveryClient discoveryClient;

    @GetMapping("/_service/{serviceName}/status")
    public Object getPoster(ProxyExchange<Object> proxy, @PathVariable("serviceName") String serviceName) {
        return proxy.uri(getServiceUri(serviceName) + "/status").get();
    }

    private String getServiceUri(String serviceName) {
        return discoveryClient.getInstances(serviceName).get(0).getUri().toString();
    }
}
