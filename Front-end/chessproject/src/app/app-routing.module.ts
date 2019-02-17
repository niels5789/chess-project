import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ChessboardComponent} from './chessboard/chessboard.component';
import {UserFormComponent} from './user-form/user-form.component';

const routes: Routes = [
  {path: '', component: UserFormComponent},
  {path: 'chessboard', component: ChessboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
