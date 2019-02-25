import { Injectable } from '@angular/core';
import {Player} from '../Player';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {any} from 'codelyzer/util/function';
import {Observable, of, pipe} from 'rxjs';
import {Game} from '../Game';
import {catchError} from 'rxjs/operators';
import {Tile} from '../Tile';


@Injectable({
  providedIn: 'root'
})
export class GamedetailsService {
  private tileUrl: 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getGamesPlayer(player: Player): Observable<Game[]> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.post<any>(`http://localhost:8080/getgamesplayer`, player, httpOptions);
  }

  getTileListGame(gameid: number): Observable<Tile[]> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.put<any>(`http://localhost:8080/stringintotilelist/${gameid}`, httpOptions);
  }
}
