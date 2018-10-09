import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class RatingService {

    constructor(private http: Http) {
    }

    canRateBook(): Observable<boolean> {
        return this.http.get("http://localhost:8080/_service/rating/status")
            .map(response => response.status == 200);
    }

    rateBook(uuid: string, rating: number): void {
        this.http.put("http://localhost:8080/api/books/" + uuid, { 'rating': rating })
            .subscribe();
    }
}