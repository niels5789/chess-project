import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {Tile} from '../Tile';
import {Player} from '../Player';
import {any} from 'codelyzer/util/function';
import {Game} from '../Game';
import {GameHistory} from '../GameHistory';

@Injectable()
export class ChessBoardService {
  private tileURL = 'http://localhost:8080/';
  constructor(private http: HttpClient) {
  }


  findAll(player: Player): Observable<Tile[]> {
    return this.http.post<any>('http://localhost:8080/returnlastgame', player).pipe(
      catchError(this.handleError<Tile>(`findAll`))
    );
  }


  returnNewBoard(player: Player): Observable<Game> {
    return this.http.post<any>(`${this.tileURL}/getnewgame`, player);
}


  saveTile(player: Player, id1: number, id2: number): Observable<Tile[]> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.put<any>(`${this.tileURL}/game/${id1}/${id2}`, player, httpOptions)/*.pipe(
      catchError(this.handleError<Tile>(`saveTile`))/!*
    )*!/*/;
  }

  piecePromotion(player: Player, promotion: string) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.post<any>(`${this.tileURL}promotion/${promotion}`, player, httpOptions);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }


  getLastGame(player: Player): Observable<Game> {
      return this.http.post<any>(`${this.tileURL}/getlastgameplayer`, player);
  }
  getTileListGame(gameid: number): Observable<Tile[]> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };
    return this.http.put<any>(`http://localhost:8080/stringintotilelist/${gameid}`, httpOptions);
  }
  getHistoryList(game: Game): Observable<GameHistory[]> {
    return this.http.post<any>(`http://localhost:8080/getgamehistorylist`, game);
  }
}
