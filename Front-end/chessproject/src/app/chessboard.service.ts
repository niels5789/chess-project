import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {Tile} from '../Tile';

@Injectable()
export class ChessBoardService {
  private tileURL = 'http://localhost:8080/';
  constructor(private http: HttpClient) {
  }


  findAll(): Observable<Tile[]> {
    return this.http.get<any>('http://localhost:8080/boards').pipe(
      catchError(this.handleError<Tile>(`findAll`))
    );
  }
  resetBoard(): Observable<Tile[]> {
    return this.http.get<any>(`${this.tileURL}/resetboard`);
  }

  saveTile(id1: number, id2: number): Observable<Tile[]> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.put<any>(`${this.tileURL}/board/${id1}/${id2}`, httpOptions);
  }

  piecePromotion(promotion: string) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.get<any>(`${this.tileURL}promotion/${promotion}`, httpOptions);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }



}
