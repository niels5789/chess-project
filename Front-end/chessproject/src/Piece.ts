
export class Piece {

  id: number;
  name: string;
  color: number;


  constructor(id: number, name: string, color: number) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  getNaam() {
    return this.name;
  }
}
