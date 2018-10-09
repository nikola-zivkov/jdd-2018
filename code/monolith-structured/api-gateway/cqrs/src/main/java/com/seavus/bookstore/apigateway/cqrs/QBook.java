/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import com.seavus.bookstore.bookcatalog.BookQueryService;
import com.seavus.bookstore.recommendation.RecommendationQueryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@javax.persistence.Entity
@Table(name = "api_gateway_cqrs_qbook")
class QBook implements BookQueryService.Book, RecommendationQueryService.Product {

    @Setter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    private String title;

    private String author;

    private String genre;

    @Setter
    private Integer rating;

    public String getCategory() {
        return getGenre();
    }
}
