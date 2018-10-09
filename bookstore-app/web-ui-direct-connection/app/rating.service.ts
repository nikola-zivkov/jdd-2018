import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class RatingService {

    constructor(private http: Http) {
    }

    getBookRating(id: string): Observable<number> {
        return this.http.get("http://localhost:8082/rating/api/products/" + id + "/rating")
            .map(response => Number.parseInt(response.text()));
    }

    rateBook(uuid: string, rating: number): void {
        this.http.put("http://localhost:8082/rating/api/products/" + uuid, { 'rating': rating })
            .subscribe();
    }
}