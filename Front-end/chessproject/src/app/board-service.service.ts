import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {Piece} from '../Piece';

@Injectable()
export class BoardServiceService {

  constructor(private http: HttpClient) {
  }


  findAll(): Observable<Piece[]> {
    return this.http.get<any>('http://localhost:8080/newboard').pipe(
      catchError(this.handleError<Piece>(`findAll`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }



}
