import { Component, OnInit } from '@angular/core';
import {Player} from '../../Player';
import {LocalStorageService} from '../local-storage.service';
import {LoginService} from '../login.service';
import {ControlContainer} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  player: Player;
  loggedInCorrect = false;
  loggedInIncorrect = false;
  register = false;

  constructor(private loginService: LoginService, private storage: LocalStorageService) { }

  ngOnInit() {
  }


  validateUser(username: string, password: string) {
    this.loginService.findPlayer(username, password).subscribe(
      result => {
        if (result.id > 0) {
          this.loggedInCorrect = true;
          this.storage.storeUser(result);
          this.player = this.storage.getStoredUser();
        } else {
          this.loggedInIncorrect = true;
        }
      }
    );
  }


  resetFlags() {
    this.loggedInIncorrect = false;
    this.loggedInCorrect = false;
    this.register = false;
  }
}
