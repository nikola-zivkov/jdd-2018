import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import { Book } from './book';

@Injectable()
export class BookService {

    constructor(private http: Http) {
    }

    findBooks(): Observable<Book[]> {
        return this.http.get("http://localhost:8080/api/books")
            .map(response => response.json());
    }
}