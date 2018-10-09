/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@javax.persistence.Entity
public class Book {

    @Setter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Setter
    private Integer rating;

    public Book(String title, String author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
