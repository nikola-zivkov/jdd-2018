/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.SpringBootApplicationConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@Import(SpringBootApplicationConfiguration.class)
@EnableEurekaClient
public class ApiGatewayCqrsServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiGatewayCqrsServiceApplication.class).run(args);
    }
}
