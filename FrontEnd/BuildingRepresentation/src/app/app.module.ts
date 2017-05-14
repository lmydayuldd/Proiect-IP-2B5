import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ErrorComponent} from './error/error.component';
import {MaterialModule} from '@angular/material';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {mainRouter} from "./app-router";
import 'hammerjs';
import {PopupComponent} from './popup/popup.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ErrorComponent,
    PopupComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    mainRouter,
    MaterialModule.forRoot(),
    BrowserAnimationsModule,
  ],
  entryComponents: [PopupComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
