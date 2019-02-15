import { Component, OnInit } from '@angular/core';
import {Tile} from '../../Tile';
import {ChessBoardService} from '../chessboard.service';

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
  onclick(idclick: number) {
    --idclick;
    if (this.clickName === null) {
      // this.oldClickid = --idclick;
      this.oldClickid = this.tilelist[idclick].id;
      this.clickName = this.tilelist[idclick].name;
      this.clickcolor = this.tilelist[idclick].color;
    } else {
      // this.tilelist[idclick].name = this.clickName;
      // this.tilelist[idclick].color = this.clickcolor;
      this.oldClickid -= 1;

      this.chessBoardService.saveTile(this.oldClickid, idclick).subscribe();
      // this.tilelist[this.oldClickid].name = null;
      // this.tilelist[this.oldClickid].color = null;
      this.clickName = null;
      this.oldClickid = null;
      this.clickcolor = null;
      window.location.reload();
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
