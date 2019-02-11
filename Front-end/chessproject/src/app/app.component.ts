import { Component, OnInit } from '@angular/core';
import {Piece} from '../Piece';
import {BoardServiceService} from './board-service.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [BoardServiceService]
})
export class AppComponent implements OnInit{

  piecelist: Piece[];

  constructor(private boardPositionService: BoardServiceService) {

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
    getPicture (id: Number) {
      // this.piecelist[]
    }

}
