/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore;

import com.seavus.bookstore.domain.Book;
import com.seavus.bookstore.domain.Genre;
import com.seavus.bookstore.service.BookService;
import com.seavus.bookstore.service.RatingService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        BookService bookService = event.getApplicationContext().getBean(BookService.class);
        RatingService ratingService = event.getApplicationContext().getBean(RatingService.class);

        Arrays.asList(books()).forEach(book -> {
            Book createdBook = bookService.createBook((String) book[0], (String) book[1], (Genre) book[2]);
            ratingService.rateBook(createdBook.getUuid(), (Integer) book[3]);
        });
    }

    private Object[][] books() {
        return new Object[][]{
                new Object[]{"It", "Stephen King", Genre.HORROR, 1},
                new Object[]{"The Shining", "Stephen King", Genre.HORROR, 3},
                new Object[]{"Misery", "Stephen King", Genre.HORROR, 5},
                new Object[]{"Dune", "Frank Herbert", Genre.SF, 1},
                new Object[]{"Solaris", "Stanislaw Lem", Genre.SF, 3},
                new Object[]{"Foundation", "Isaac Asimov", Genre.SF, 3}
        };
    }
}
