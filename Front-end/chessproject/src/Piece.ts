
export class Piece {

  id: Number;
  name: String;
  color: Color;


  constructor(id: Number, name: String, color: Color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  getNaam() {
    return this.name;
  }
}
