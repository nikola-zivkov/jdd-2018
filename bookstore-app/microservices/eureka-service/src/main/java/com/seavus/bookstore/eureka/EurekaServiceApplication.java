/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.eureka;

import com.seavus.SpringBootApplicationConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Import;

@Import(SpringBootApplicationConfiguration.class)
@EnableEurekaServer
public class EurekaServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServiceApplication.class).run(args);
    }
}
