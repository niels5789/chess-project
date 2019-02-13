
export class Tile {

  id: number;
  name: string;
  color: number;
  xCo: number;
  yCo: number;

  constructor(id: number, name: string, color: number, xCo: number, yCo: number) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.xCo = xCo;
    this.yCo = yCo;
  }

  getNaam() {
    return this.name;
  }
}
