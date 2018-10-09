/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog;

import com.seavus.SpringBootApplicationConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@Import(SpringBootApplicationConfiguration.class)
@EnableEurekaClient
public class BookCatalogServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BookCatalogServiceApplication.class).run(args);
    }
}
