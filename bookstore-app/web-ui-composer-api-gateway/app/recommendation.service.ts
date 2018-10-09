import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import { Book } from './book';

@Injectable()
export class RecommendationService {

    constructor(private http: Http) {
    }

    recommendSimilarBooks(uuid: string): Observable<Book[]> {
        return this.http.get("http://localhost:8080/api/books/" + uuid + "/similar")
            .map(response => response.json());
    }
}