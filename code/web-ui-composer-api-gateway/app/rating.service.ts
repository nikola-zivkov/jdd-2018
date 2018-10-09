import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class RatingService {

    constructor(private http: Http) {
    }

    rateBook(uuid: string, rating: number): void {
        this.http.put("http://localhost:8080/api/books/" + uuid, { 'rating': rating })
            .subscribe();
    }
}