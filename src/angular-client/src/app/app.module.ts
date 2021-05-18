import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DecodeFileComponent } from './components/decode-file/decode-file.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import { HistoryComponent } from './components/history/history.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    DecodeFileComponent,
    ProductDetailsComponent,
    LoginComponent,
    HistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
