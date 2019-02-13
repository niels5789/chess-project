import { Component, OnInit } from '@angular/core';
import {Tile} from '../Tile';
import {BoardServiceService} from './board-service.service';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [BoardServiceService]
})
export class AppComponent implements OnInit {

  tilelist: Tile[];
  private oldClickid: number = null;
  private clickName: string = null;
  private clickcolor: number = null;


  constructor(private boardPositionService: BoardServiceService) {

  }

  ngOnInit() {
    this.getCurrentPosition();
  }


  getCurrentPosition() {
    this.boardPositionService.findAll().subscribe(
      tilelist => {
        this.tilelist = tilelist;
      },
      err => {
        console.log(err);
      }
    );
  }

  getPicture(id: number) {
      id--;
      if (this.tilelist[id].color === 1) {
        switch (this.tilelist[id].name) {
          case 'Loper':
            return '&#9821;';
          case 'Toren':
            return '&#9820;';
          case 'Paard':
            return '&#9822;';
          case 'Koningin':
            return '&#9818;';
          case 'Koning':
            return '&#9819;';
          case 'Pion':
            return '&#9823;';
        }
      } else if (this.tilelist[id].color === 0) {
        switch (this.tilelist[id].name) {
          case 'Toren':
            return '&#9814;';
          case 'Paard':
            return '&#9816;';
            case 'Loper':
            return '&#9815;';
          case 'Koningin':
            return '&#9813';
          case 'Koning':
            return '&#9812;';
          case 'Pion':
            return '&#9817;';
        }
      }

    }
    onclick (idclick: number) {
      --idclick;
        if(this.clickName === null) {
          // this.oldClickid = --idclick;
          this.oldClickid = this.tilelist[idclick].id;
          this.clickName = this.tilelist[idclick].name;
          this.clickcolor = this.tilelist[idclick].color;
        } else {
          // this.tilelist[idclick].name = this.clickName;
          // this.tilelist[idclick].color = this.clickcolor;
          this.oldClickid -= 1;

          this.boardPositionService.saveTile(this.oldClickid, idclick).subscribe();
          // this.tilelist[this.oldClickid].name = null;
          // this.tilelist[this.oldClickid].color = null;
          this.clickName = null;
          this.oldClickid = null;
          this.clickcolor = null;
          window.location.reload();
        }

  }
}
