import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { BookListComponent } from './book-list.component';

@NgModule({
  imports: [BrowserModule, HttpModule],
  declarations: [AppComponent, BookListComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
