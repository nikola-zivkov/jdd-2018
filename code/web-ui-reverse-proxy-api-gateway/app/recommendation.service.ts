import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import { Book } from './book';
import { BookService } from "./book.service";

@Injectable()
export class RecommendationService {

    constructor(private http: Http, private bookService: BookService) {
    }

    recommendSimilarBooks(uuid: string): Observable<Book[]> {
        return this.http.get("http://localhost:8080/recommendation/api/products/" + uuid + "/similar")
            .map(response => response.json() as Book[])
            .flatMap(similarBooks => Observable.from(similarBooks))
            .flatMap(similarBook => {
                return this.bookService.findBook(similarBook.uuid).map(book => {
                    similarBook.title = book.title;
                    return similarBook;
                }).catch(() => Observable.from([]));
            })
            .toArray();
    }
}