export class GameHistory {
  id: number;
  countPlace: number;
  boardPosition: string;
  vanColor: string;
  van: string;
  naar: string;


  constructor(id: number, countPlace: number, boardPosition: string, vanColor: string, van: string, naar: string) {
    this.id = id;
    this.countPlace = countPlace;
    this.boardPosition = boardPosition;
    this.vanColor = vanColor;
    this.van = van;
    this.naar = naar;
  }
}
