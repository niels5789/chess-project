import { Component, OnInit } from '@angular/core';
import {Game} from '../../Game';
import {GamedetailsService} from '../gamedetails.service';
import {LocalStorageService} from '../local-storage.service';
import {Tile} from '../../Tile';
import {ChessBoardService} from '../chessboard.service';

@Component({
  selector: 'app-gamedetails',
  templateUrl: './gamedetails.component.html',
  styleUrls: ['./gamedetails.component.css'],
  providers: [GamedetailsService]
})
export class GamedetailsComponent implements OnInit {

  gamelist: Game[];
  tiletilelist1: Tile[];
  tilelist1MoveCount: number;
  tiletilelist2: Tile[];
  tilelist2MoveCount: number;
  tiletilelist3: Tile[];
  tilelist3MoveCount: number;
  tiletilelist4: Tile[];
  tilelist4MoveCount: number;
  tiletilelist5: Tile[];
  tilelist5MoveCount: number;

  count: number;
  private x: number;
  private y = 0;
  private nextChar: string;
  private colorPiece: number;
  private piecename: string;
  private color = 'white';
  private tileCounter = 0;

  constructor(private gamedetailsService: GamedetailsService, private storage: LocalStorageService) {
  }

  ngOnInit() {
    this.gamelist = null;
    this.tiletilelist1 = null;
    this.tiletilelist2 = null;
    this.tiletilelist3 = null;
    this.tiletilelist4 = null;
    this.tiletilelist5 = null;

    this.gamedetailsService.getGamesPlayer(this.storage.getStoredUser()).subscribe(
      gamelist => {
        this.gamelist = gamelist;
        this.retrievingLists();
      }, err => {
        console.log(err);
      }
    );

  }

  retrievingLists() {
    this.gamedetailsService.getTileListGame(this.gamelist[0].id).subscribe(
      tilelist => {
        this.tiletilelist1 = tilelist;
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[1].id).subscribe(
      tilelist => {
        this.tiletilelist2 = tilelist;
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[2].id).subscribe(
      tilelist => {
        this.tiletilelist3 = tilelist;
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[3].id).subscribe(
      tilelist => {
        this.tiletilelist4 = tilelist;
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[4].id).subscribe(
      tilelist => {
        this.tiletilelist5 = tilelist;
      }, err => {
        console.log(err);
      }
    );

    this.tilelist1MoveCount = this.gamelist[0].moveCount;
    this.tilelist2MoveCount = this.gamelist[1].moveCount;
    this.tilelist3MoveCount = this.gamelist[2].moveCount;
    this.tilelist4MoveCount = this.gamelist[3].moveCount;
    this.tilelist5MoveCount = this.gamelist[4].moveCount;
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
  getPicture(gamenumber: number, tileid: number) {

    if (gamenumber === 0) {
      tileid--;
      if (this.tiletilelist1[tileid].color === 1) {
        switch (this.tiletilelist1[tileid].name) {
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
      } else if (this.tiletilelist1[tileid].color === 0) {
        switch (this.tiletilelist1[tileid].name) {
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

    } else if (gamenumber === 1) {
      tileid--;
      if (this.tiletilelist2[tileid].color === 1) {
        switch (this.tiletilelist2[tileid].name) {
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
      } else if (this.tiletilelist2[tileid].color === 0) {
        switch (this.tiletilelist2[tileid].name) {
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

    } else if (gamenumber === 2) {
      tileid--;
      if (this.tiletilelist3[tileid].color === 1) {
        switch (this.tiletilelist3[tileid].name) {
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
      } else if (this.tiletilelist3[tileid].color === 0) {
        switch (this.tiletilelist3[tileid].name) {
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
    } else if (gamenumber === 3) {
      tileid--;
      if (this.tiletilelist4[tileid].color === 1) {
        switch (this.tiletilelist4[tileid].name) {
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
      } else if (this.tiletilelist4[tileid].color === 0) {
        switch (this.tiletilelist4[tileid].name) {
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

    } else if (gamenumber === 4) {
      tileid--;
      if (this.tiletilelist5[tileid].color === 1) {
        switch (this.tiletilelist5[tileid].name) {
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
      } else if (this.tiletilelist5[tileid].color === 0) {
        switch (this.tiletilelist5[tileid].name) {
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
  }

  gameInList(id: number) {
    if(this.tiletilelist1[0] === null) {
      this.gamedetailsService.getTileListGame(id).subscribe(
        tilelist => {
          this.tiletilelist1 = tilelist;

        }
      );
    } else {
      return `<button>this.getPicture1(1)</button>`;
    }


  }

  setHistoryGameMin(gameid: number, index: number) {
    if (index === 0) {
      this.tilelist1MoveCount -= 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist1MoveCount).subscribe(
        tilelist => {
          this.tiletilelist1 = tilelist;
        }
      );
    }
    if (index === 1) {
      this.tilelist2MoveCount -= 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist2MoveCount).subscribe(
        tilelist => {
          this.tiletilelist2 = tilelist;
        }
      );
    }
    if (index === 2) {
      this.tilelist3MoveCount -= 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist3MoveCount).subscribe(
        tilelist => {
          this.tiletilelist3 = tilelist;
        }
      );
    }
    if (index === 3) {
      this.tilelist4MoveCount -= 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist4MoveCount).subscribe(
        tilelist => {
          this.tiletilelist4 = tilelist;
        }
      );
    }
    if (index === 4) {
      this.tilelist5MoveCount -= 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist5MoveCount).subscribe(
        tilelist => {
          this.tiletilelist5 = tilelist;
        }
      );
    }
  }
  setHistoryGamePlus(gameid: number, index: number) {
    if (index === 0) {
      this.tilelist1MoveCount += 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist1MoveCount).subscribe(
        tilelist => {
          this.tiletilelist1 = tilelist;
        }
      );
    }
    if (index === 1) {
      this.tilelist2MoveCount += 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist2MoveCount).subscribe(
        tilelist => {
          this.tiletilelist2 = tilelist;
        }
      );
    }
    if (index === 2) {
      this.tilelist3MoveCount += 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist3MoveCount).subscribe(
        tilelist => {
          this.tiletilelist3 = tilelist;
        }
      );
    }
    if (index === 3) {
      this.tilelist4MoveCount += 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist4MoveCount).subscribe(
        tilelist => {
          this.tiletilelist4 = tilelist;
        }
      );
    }
    if (index === 4) {
      this.tilelist5MoveCount += 1;
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist5MoveCount).subscribe(
        tilelist => {
          this.tiletilelist5 = tilelist;
        }
      );
    }
  }
}
