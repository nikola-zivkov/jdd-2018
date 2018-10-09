/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.bookcatalog.api;

import java.util.List;

public interface BookService {

    Book findBook(String uuid);

    List<Book> findBooks();
}
