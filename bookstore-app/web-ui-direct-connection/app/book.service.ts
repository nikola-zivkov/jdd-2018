import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import { Book } from './book';
import { RatingService } from "./rating.service";

@Injectable()
export class BookService {

    constructor(private http: Http, private ratingService: RatingService) {
    }

    findBook(uuid: string): Observable<Book> {
        return this.http.get("http://localhost:8081/book-catalog/api/books/" + uuid)
            .map(response => response.json());
    }

    findBooks(): Observable<Book[]> {
        return this.http.get("http://localhost:8081/book-catalog/api/books")
            .map(response => response.json() as Book[])
            .flatMap(books => Observable.from(books))
            .flatMap(book => {
                return this.ratingService.getBookRating(book.uuid).map(rating => {
                    book.rating = rating;
                    return book;
                }).catch(() => Observable.of(book));
            })
            .toArray();
    }
}