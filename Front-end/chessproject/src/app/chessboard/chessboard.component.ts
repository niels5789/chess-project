import { Component, OnInit } from '@angular/core';
import {Tile} from '../../Tile';
import {ChessBoardService} from '../chessboard.service';
import {$} from 'protractor';

@Component({
  selector: 'app-chessboard',
  templateUrl: './chessboard.component.html',
  styleUrls: ['./chessboard.component.css'],
  providers: [ChessBoardService]
})
export class ChessboardComponent implements OnInit {
  tilelist: Tile[];
  private oldClickid: number = null;
  private clickName: string = null;
  private clickcolor: number = null;
  private idclick: number;

  constructor(private chessBoardService: ChessBoardService) {  }

  ngOnInit() {
    this.getCurrentPosition();
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

  }

  onclick (idclick: number) {
    idclick--;
    this.idclick = idclick;
    if (this.clickName === null) {

      this.oldClickid = this.tilelist[idclick].id;
      this.clickName = this.tilelist[idclick].name;
      this.clickcolor = this.tilelist[idclick].color;


    } else {
      this.oldClickid -= 1;
      this.chessBoardService.saveTile(this.oldClickid, idclick).subscribe();
      this.chessBoardService.findAll().subscribe(
        tilelist => {
          this.tilelist = tilelist;
        },
        err => {
          console.log(err);
        }
      );
      document.getElementById('button' + this.oldClickid).innerHTML = '';
      document.getElementById('button' + this.idclick).innerHTML = this.getPicture(this.oldClickid);
      this.clickName = null;
      this.oldClickid = null;
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
}
