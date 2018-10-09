/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.composer;

import com.seavus.SpringBootApplicationConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@Import(SpringBootApplicationConfiguration.class)
@EnableEurekaClient
public class ApiGatewayComposerServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiGatewayComposerServiceApplication.class).run(args);
    }
}
