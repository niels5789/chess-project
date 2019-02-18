export class Player {
  id: number;
  username: string;
  password: string;
  playerName: string;

  constructor(id: number, username: string, password: string, playerName: string) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.playerName = playerName;
  }
}
