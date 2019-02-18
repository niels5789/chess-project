import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ChessboardComponent} from './chessboard/chessboard.component';
import {UserFormComponent} from './user-form/user-form.component';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  {path: 'aanmelden', component: UserFormComponent},
  {path: 'chessboard', component: ChessboardComponent},
  {path: '', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
