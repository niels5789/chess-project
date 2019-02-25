import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ChessboardComponent } from './chessboard/chessboard.component';
import { LoginComponent } from './login/login.component';
import { UserFormComponent } from './user-form/user-form.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LocalStorageService} from './local-storage.service';
import {StorageServiceModule} from 'ngx-webstorage-service';
import { GamedetailsComponent } from './gamedetails/gamedetails.component';

@NgModule({
  declarations: [
    AppComponent,
    ChessboardComponent,
    LoginComponent,
    UserFormComponent,
    GamedetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    StorageServiceModule
  ],
  providers: [LocalStorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
