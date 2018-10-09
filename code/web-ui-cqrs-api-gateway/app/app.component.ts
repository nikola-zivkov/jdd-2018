import { Component } from '@angular/core';

import { BookService } from './book.service';
import { RatingService } from './rating.service';
import { RecommendationService } from './recommendation.service';

@Component({
    selector: 'book-store-app',
    template: `
    <book-list></book-list>
    `,
    providers: [BookService, RatingService, RecommendationService]
})
export class AppComponent {

}
