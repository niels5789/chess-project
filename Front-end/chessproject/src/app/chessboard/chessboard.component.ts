import { Component, OnInit } from '@angular/core';
import {Tile} from '../../Tile';
import {ChessBoardService} from '../chessboard.service';
import {$, element} from 'protractor';
import {Player} from '../../Player';
import {LocalStorageService} from '../local-storage.service';
import {LoginService} from '../login.service';
import {RouterLink} from '@angular/router';
import {Message} from '@angular/compiler/src/i18n/i18n_ast';
import {MessageBundle} from '@angular/compiler';

@Component({
  selector: 'app-chessboard',
  templateUrl: './chessboard.component.html',
  styleUrls: ['./chessboard.component.css'],
  providers: [ChessBoardService]
})
export class ChessboardComponent implements OnInit {
  tilelist: Tile[];
  private firstClick = -11;
  private secondClick = -22;
  player: Player;
  errorMessage = '';
  private color = 'white';
  private tileCounter = 0;


  constructor(private chessBoardService: ChessBoardService, private loginService: LoginService, private storage: LocalStorageService) {
  }

  ngOnInit() {
    this.getCurrentPosition();
    this.player = this.storage.getStoredUser();

  }

  getPicture(id: number) {
    id--;
    if (this.tilelist[id].color === 1) {
      switch (this.tilelist[id].name) {
        case 'Bishop':
          return '&#9821;';
        case 'Rook':
          return '&#9820;';
        case 'Knight':
          return '&#9822;';
        case 'Queen':
          return '&#9819;';
        case 'King':
          return '&#9818;';
        case 'Pawn':
          return '&#9823;';
      }
    } else if (this.tilelist[id].color === 0) {
      switch (this.tilelist[id].name) {
        case 'Rook':
          return '&#9814;';
        case 'Knight':
          return '&#9816;';
        case 'Bishop':
          return '&#9815;';
        case 'Queen':
          return '&#9813';
        case 'King':
          return '&#9812;';
        case 'Pawn':
          return '&#9817;';
      }
    }
    return '';

  }

  onclick(thisClick: number) {
    this.errorMessage = '';
    if (this.firstClick === -11) {
      this.firstClick = thisClick - 1;
    } else if (this.secondClick === -22 ) {
      if (this.firstClick === (thisClick - 1)) {
        this.firstClick = -11;
        this.secondClick = -22;
      } else {
        this.secondClick = thisClick - 1;
        this.chessBoardService.saveTile(this.storage.getStoredUser(), this.firstClick, this.secondClick).subscribe(
              tilelist => {
                this.tilelist = tilelist;
                this.errorMessage = '';
                this.firstClick = -11;
                this.secondClick = -22;
              },
              err => {
                this.firstClick = -11;
                this.secondClick = -22;
                this.errorMessage = 'Invalid move';
                console.log(err);
              }
        );
      }
    }
  }


  getCurrentPosition() {
    this.chessBoardService.findAll().subscribe(
      tilelist => {
        this.tilelist = tilelist;
      },
      err => {
        console.log(err);
      }
    );
  }

  changePlayerName(playername: string) {


    this.loginService.savePlayerName(this.storage.getStoredUser().username, playername).subscribe(
      result => {
        this.player = result;
        this.storage.storeUser(this.player);
      }
    );

  }

  resetBoard() {
    this.chessBoardService.returnNewBoard(this.storage.getStoredUser()).subscribe(
      tilelist => {
        this.tilelist = tilelist;
      },
      err => {
        console.log(err);
      }
    );
  }

  getPromotion(promotion: string) {
    this.chessBoardService.piecePromotion(promotion).subscribe(
      tilelist => {
        this.tilelist = tilelist;
      },
      err => {
        console.log(err);
      }
    );
  }

  colorPicker() {

    if (this.tileCounter % 8 !== 0) {
      if (this.color === 'white') {
        this.color = 'black';
      } else {
        this.color = 'white';
      }
    }
    this.tileCounter++;

    return this.color;
  }
}


