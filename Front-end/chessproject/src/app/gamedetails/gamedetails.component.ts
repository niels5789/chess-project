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

  private color = 'white';
  tileCounter = 0;
  stringid: number;

  constructor(private gamedetailsService: GamedetailsService, private storage: LocalStorageService) {
  }

  ngOnInit() {
    // this.gamelist = null;
    // this.tiletilelist1 = null;
    // this.tiletilelist2 = null;
    // this.tiletilelist3 = null;
    // this.tiletilelist4 = null;
    // this.tiletilelist5 = null;

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
        this.tilelist1MoveCount = this.gamelist[0].moveCount;
        this.tileCounter = 0;
        this.color = 'white';

      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[1].id).subscribe(
      tilelist => {
        this.tiletilelist2 = tilelist;
        this.tilelist2MoveCount = this.gamelist[1].moveCount;
        this.tileCounter = 0;
        this.color = 'white';
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[2].id).subscribe(
      tilelist => {
        this.tiletilelist3 = tilelist;
        this.tilelist3MoveCount = this.gamelist[2].moveCount;
        this.tileCounter = 0;
        this.color = 'white';
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[3].id).subscribe(
      tilelist => {
        this.tiletilelist4 = tilelist;
        this.tilelist4MoveCount = this.gamelist[3].moveCount;
        this.tileCounter = 0;
        this.color = 'white';
      }, err => {
        console.log(err);
      }
    );
    this.gamedetailsService.getTileListGame(this.gamelist[4].id).subscribe(
      tilelist => {
        this.tiletilelist5 = tilelist;
        this.tilelist5MoveCount = this.gamelist[4].moveCount;
        this.tileCounter = 0;
        this.color = 'white';
      }, err => {
        console.log(err);
      }
    );

  }

  colorPicker() {

    if (this.tileCounter  % 8  !== 0) {
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
    console.log(tileid);
    tileid--;
    if (gamenumber === 0) {
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


  setHistoryGameMin(gameid: number, index: number) {
    if (index === 0) {
      this.tilelist1MoveCount -= 1;
      if (this.tilelist1MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist1MoveCount) {
        this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist1MoveCount).subscribe(
          tilelist => {
            this.tiletilelist1 = tilelist;
          }
        );
      } else {
        this.tilelist1MoveCount++;
      }

    }
    if (index === 1) {
      this.tilelist2MoveCount -= 1;
      if (this.tilelist2MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist2MoveCount) {
        this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist2MoveCount).subscribe(
          tilelist => {
            this.tiletilelist2 = tilelist;
          }
        );
      } else {
        this.tilelist2MoveCount++;
      }
    }
    if (index === 2) {
      this.tilelist3MoveCount -= 1;
      if (this.tilelist3MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist3MoveCount) {
        this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist3MoveCount).subscribe(
          tilelist => {
            this.tiletilelist3 = tilelist;
          }
        );
      } else {
        this.tilelist3MoveCount++;
      }
    }
    if (index === 3) {
      this.tilelist4MoveCount -= 1;
      if (this.tilelist4MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist4MoveCount) {
        this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist4MoveCount).subscribe(
          tilelist => {
            this.tiletilelist4 = tilelist;
          }
        );
      } else {
        this.tilelist4MoveCount++;
      }
    }
    if (index === 4) {
      this.tilelist5MoveCount -= 1;
      if (this.tilelist5MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist5MoveCount) {
        this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist5MoveCount).subscribe(
          tilelist => {
            this.tiletilelist5 = tilelist;
          }
        );
      } else { this.tilelist5MoveCount++; }
    }
  }
  setHistoryGamePlus(gameid: number, index: number) {
    if (index === 0) {
      this.tilelist1MoveCount += 1;
      if (this.tilelist1MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist1MoveCount) {
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist1MoveCount).subscribe(
        tilelist => {
          this.tiletilelist1 = tilelist;
        }
      );
    } else {this.tilelist1MoveCount--; }
    }
    if (index === 1) {
      this.tilelist2MoveCount += 1;
      if (this.tilelist2MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist2MoveCount) {
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist2MoveCount).subscribe(
        tilelist => {
          this.tiletilelist2 = tilelist;
        }
      );
    } else { this.tilelist2MoveCount--; }
    }
    if (index === 2) {
      this.tilelist3MoveCount += 1;
      if (this.tilelist3MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist3MoveCount) {
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist3MoveCount).subscribe(
        tilelist => {
          this.tiletilelist3 = tilelist;
        }
      );
    } else {this.tilelist3MoveCount--; }
    }
    if (index === 3) {
      this.tilelist4MoveCount += 1;
      if (this.tilelist4MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist4MoveCount) {
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist4MoveCount).subscribe(
        tilelist => {
          this.tiletilelist4 = tilelist;
        }
      );
    } else { this.tilelist4MoveCount--; }
    }
    if (index === 4) {
      this.tilelist5MoveCount += 1;
      if (this.tilelist5MoveCount >= 0 && this.gamelist[index].moveCount >= this.tilelist5MoveCount) {
      this.gamedetailsService.getTileListGameHistory(gameid, this.tilelist5MoveCount).subscribe(
        tilelist => {
          this.tiletilelist5 = tilelist;
        }
      );
    } else { this.tilelist5MoveCount--; }
    }
  }

  changeCounter() {
    this.tileCounter = 0;
  }

  returnCount(i: number) {
    if (i === 0) {
      return this.tilelist1MoveCount;
    } else if (i === 1) {
      return this.tilelist2MoveCount;
    } else if (i === 2) {
      return this.tilelist3MoveCount;
    } else if (i === 3) {
      return this.tilelist4MoveCount;
    } else if (i === 4) {
      return this.tilelist5MoveCount;
    }
  }

  changeList(i: number) {

    if (document.getElementById('i').valueOf() === '0') {
      this.stringid = 0;
      this.gamedetailsService.getTileListGame(this.gamelist[this.stringid].id).subscribe(
        tilelist => {
          this.tiletilelist1 = tilelist;
        }
      );

    }
  }
}
