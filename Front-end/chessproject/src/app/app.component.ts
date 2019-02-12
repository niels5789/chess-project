import { Component, OnInit } from '@angular/core';
import {Piece} from '../Piece';
import {BoardServiceService} from './board-service.service';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [BoardServiceService]
})
export class AppComponent implements OnInit {

  piecelist: Piece[];
  private oldClickid: number = null;
  private clickName: string = null;
  private clickcolor: number = null;

  constructor(private boardPositionService: BoardServiceService, private $http: HttpClient) {

  }

  ngOnInit() {
    this.getCurrentPosition();
  }


  getCurrentPosition() {
    this.boardPositionService.findAll().subscribe(
      piecelist => {
        this.piecelist = piecelist;
      },
      err => {
        console.log(err);
      }
    );
  }

  getPicture(id: number) {
      id--;
      if (this.piecelist[id].color === 1) {
        switch (this.piecelist[id].name) {
          case 'Loper':
            return '&#9820;';
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
      } else if (this.piecelist[id].color === 0) {
        switch (this.piecelist[id].name) {
          case 'Loper':
            return '&#9815;';
          case 'Toren':
            return '&#9814;';
          case 'Paard':
            return '&#9816;';
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
          this.oldClickid = this.piecelist[idclick].id;
          this.clickName = this.piecelist[idclick].name;
          this.clickcolor = this.piecelist[idclick].color;
        } else {
          this.piecelist[idclick].name = this.clickName;
          this.piecelist[idclick].color = this.clickcolor;
          this.oldClickid -= 1;
          this.piecelist[this.oldClickid].name = null;
          this.piecelist[this.oldClickid].color = null;
          this.clickName = null;
          this.oldClickid = null;
          this.clickcolor = null;
        }
        // this.getCurrentPosition();
  }
}
