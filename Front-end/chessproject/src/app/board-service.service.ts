import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {Tile} from '../Tile';
import {buildPath} from 'selenium-webdriver/http';

@Injectable()
export class BoardServiceService {
private tileURL = 'http://localhost:8080/';
  constructor(private http: HttpClient) {
  }


  findAll(): Observable<Tile[]> {
    return this.http.get<any>('http://localhost:8080/boards').pipe(
      catchError(this.handleError<Tile>(`findAll`))
    );
  }

  saveTile(id1: number, id2: number) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
        return this.http.put(`${this.tileURL}/board/${id1}/${id2}`, httpOptions).pipe();
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }



}
