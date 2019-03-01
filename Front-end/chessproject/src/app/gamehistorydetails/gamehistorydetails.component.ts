import {Component, Input, OnInit} from '@angular/core';
import {GamedetailsService} from '../gamedetails.service';
import {Tile} from '../../Tile';
import {GameHistory} from '../../GameHistory';
import {Game} from '../../Game';

@Component({
  selector: 'app-gamehistorydetails',
  templateUrl: './gamehistorydetails.component.html',
  styleUrls: ['./gamehistorydetails.component.css'],
  providers: [GamedetailsService]
})
export class GamehistorydetailsComponent implements OnInit {

  constructor(private gameDetailsService: GamedetailsService) {
  }

  @Input('gameidInput') game: Game;
  tileList: Tile[];
  tilelisthistory: GameHistory;
  tilelistmovecount: number;
  private color = 'white';
  tileCounter = 0;


  ngOnInit() {
    this.gameDetailsService.getTileListGameHistory(this.game.id, this.game.moveCount).subscribe(
      tilelist => {
        this.tileList = tilelist;
        this.tilelistmovecount = this.game.moveCount;
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


  getPicture(tileid: number) {
    tileid--;
    if (this.tileList[tileid].color === 1) {
      switch (this.tileList[tileid].name) {
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
    } else if (this.tileList[tileid].color === 0) {
      switch (this.tileList[tileid].name) {
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

  setHistoryGameMin() {
      this.tilelistmovecount -= 1;
      if (this.tilelistmovecount >= 0 && this.game.moveCount >= this.tilelistmovecount) {
        this.gameDetailsService.getTileListGameHistory(this.game.id, this.tilelistmovecount).subscribe(
          tilelist => {
            this.tileList = tilelist;
          }
        );
      } else {
        this.tilelistmovecount++;
      }

    }

  setHistoryGamePlus() {
      this.tilelistmovecount += 1;
      if (this.tilelistmovecount >= 0 && this.game.moveCount >= this.tilelistmovecount) {
        this.gameDetailsService.getTileListGameHistory(this.game.id, this.tilelistmovecount).subscribe(
          tilelist => {
            this.tileList = tilelist;
          }
        );
      } else {this.tilelistmovecount--; }
    }
}
