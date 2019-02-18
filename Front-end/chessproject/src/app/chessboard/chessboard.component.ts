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
  private oldClickid: number;
  private aanDeBeurt: number = 0;

  constructor(private chessBoardService: ChessBoardService) {
  }

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
    return '';

  }

  onclick(idclick: number) {


      if (this.aanDeBeurt === 0) {
        idclick--;
        this.oldClickid = this.tilelist[idclick].id;
        this.aanDeBeurt++;
      } else if (this.aanDeBeurt === 1) {
        this.aanDeBeurt = 0;
        this.oldClickid--;
        --idclick;
        this.chessBoardService.saveTile(this.oldClickid, idclick).subscribe(
          tilelist => {
            this.tilelist = tilelist;
          },
          err => {
            console.log(err);
          }
        );


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


