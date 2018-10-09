import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { Book } from './book';
import { BookService } from './book.service';
import { RatingService } from './rating.service';
import { RecommendationService } from './recommendation.service';

@Component({
    selector: 'book-list',
    styleUrls: ['app/font-awesome.css', 'app/rating.css', 'app/booklist.css'],
    template: `
    <h3 class="title">Bookstore</h3>
    <div class="container">
    <div class="row">
        <div *ngFor="let book of books; let i = index" class="col-xs-2 text-center book" [class.selected]="book == selectedBook">
            <img (click)="showBookDetails(book.uuid);" src="http://localhost:8080/poster/{{book.title}}.jpg" class="img-thumbnail" style="height: 125px"/>
            <div class="book-title">{{book.title}}</div>
            <div class="book-author">{{book.author}}</div>
            <div class="book-genre">{{book.genre}}</div>
            <div *ngIf="book.rating" [innerHTML]="book.rating + ' / 5'"></div>
        </div>
        </div>
    </div>
    <div style="widht:100%; border-top: 1px solid #f2f2f2"></div>
    <div class="row selectedBook" *ngIf="selectedBook">
        <div class="col-xs-10 book-info">
            <img src="http://localhost:8080/poster/{{selectedBook.title}}.jpg" class="img-thumbnail" style="width: 175px"/>
            <div id="book-description">
            <div class="book-title">{{selectedBook.title}}</div>
            <div>by {{selectedBook.author}}</div>
            <div>Genre: {{selectedBook.genre}}</div>
            <fieldset class="rating" *ngIf="selectedBook.rating">
                <input type="radio" [checked]="selectedBook.rating === 5" id="star5" name="rating" value="5" (click)="rateBook(selectedBook.uuid, 5)"/><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                <input type="radio" [checked]="selectedBook.rating === 4" id="star4" name="rating" value="4" (click)="rateBook(selectedBook.uuid, 4)"/><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                <input type="radio" [checked]="selectedBook.rating === 3" id="star3" name="rating" value="3" (click)="rateBook(selectedBook.uuid, 3)"/><label class = "full" for="star3" title="Meh - 3 stars"></label>
                <input type="radio" [checked]="selectedBook.rating === 2" id="star2" name="rating" value="2" (click)="rateBook(selectedBook.uuid, 2)"/><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                <input type="radio" [checked]="selectedBook.rating === 1" id="star1" name="rating" value="1" (click)="rateBook(selectedBook.uuid, 1)"/><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
            </fieldset>
            <div class="book-intro">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc consectetur lectus lacinia libero gravida efficitur. Phasellus et massa magna. Duis posuere velit egestas tortor tempor porta. Suspendisse rhoncus faucibus quam vestibulum malesuada. Duis ex justo, posuere ac feugiat bibendum, egestas ac ligula. Nunc ultricies et velit eget accumsan.
            </div>
            </div>
        </div>
        <div *ngIf="recommendedBooks && recommendedBooks.length" class="col-xs-2 recommendation">
            <h3>Recommended similar</h3>
            <div *ngFor="let recommendedBook of recommendedBooks" class="col-xs-2 recommended-book">
                <img src="http://localhost:8080/poster/{{recommendedBook.title}}.jpg" class="img-thumbnail" style="height: 125px"/>
                <div class="book-title">{{recommendedBook.title}}</div>
                <div>{{recommendedBook.author}}</div>
                <div *ngIf="recommendedBook.rating" class="text-center">{{recommendedBook.rating}} / 5</div>
            </div>
        </div>
    </div>
    `
})
export class BookListComponent implements OnInit {

    private books: Book[];
    private selectedBook: Book;
    private recommendedBooks: Book[];

    constructor(private bookService: BookService, private ratingService: RatingService, private recommendationService: RecommendationService) {
    }

    ngOnInit(): void {
        this.bookService.findBooks().subscribe(books => this.books = books);
    }

    showBookDetails(uuid: string): void {
        this.selectedBook = this.books.find(book => book.uuid == uuid);
        this.recommendationService.recommendSimilarBooks(uuid).subscribe(
            recommendedBooks => this.recommendedBooks = recommendedBooks,
            error => this.recommendedBooks = []);
    }

    rateBook(uuid: string, rating: number): void {
        this.selectedBook.rating = rating;
        this.ratingService.rateBook(uuid, rating);
    }
}