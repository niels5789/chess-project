import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Player} from '../Player';
import {catchError} from 'rxjs/operators';
import {Tile} from '../Tile';
import {LocalStorageService} from './local-storage.service';

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private tileURL = 'http://localhost:8080';
 // listplayers: Player[];

  constructor(private http: HttpClient, private storage: LocalStorageService) { }

  savePlayer(username: string, password: string): Observable<Player[]> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    return this.http.put<any>(`${this.tileURL}/checkplayer/${username}/${password}`, httpOptions);

  }
  findPlayer(username: string, password: string): Observable<Player> {
    return this.http.post<any>(`${this.tileURL}/authenticate`, new Player(0, username, password, ''));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  savePlayerName(username: string, playername: string): Observable<Player> {
    return this.http.post<any>(`${this.tileURL}/changeplayername`, new Player(0, username, '', playername));
  }
}
