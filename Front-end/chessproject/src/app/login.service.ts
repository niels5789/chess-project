import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Player} from '../Player';
import {catchError} from 'rxjs/operators';
import {Tile} from '../Tile';

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private tileURL = 'http://localhost:8080/';
 listplayers: Player[];

  constructor(private http: HttpClient) { }

  saveTile(username: string, password: string): Observable<Player[]> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    return this.http.put<any>(`${this.tileURL}/checkplayer/${username}/${password}`, httpOptions).pipe();

  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
