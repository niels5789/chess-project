import { Component, OnInit } from '@angular/core';
import {Player} from '../../Player';
import {LocalStorageService} from '../local-storage.service';
import {LoginService} from '../login.service';
import {ControlContainer} from '@angular/forms';
import sha1 from 'sha1';
import {Router} from '@angular/router';

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
  firstName: string;

  constructor(private loginService: LoginService,
              private storage: LocalStorageService,
              private router: Router) { }

  ngOnInit() {
    if(this.storage.getStoredUser() != null) {
      this.router.navigateByUrl('/chessboard');
    }
  }



  validateUser(username: string, password: string) {
    this.loginService.findPlayer(username, sha1(password)).subscribe(
      result => {
        if (result.id > 0) {
          this.loggedInCorrect = true;
          this.storage.storeUser(result);
          this.player = this.storage.getStoredUser();
          this.firstName = this.player.username;
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
