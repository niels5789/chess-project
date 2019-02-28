import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {LoginService} from '../login.service';
import {Player} from '../../Player';
import sha1 from 'sha1';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
  providers: [LoginService]
})
export class UserFormComponent implements OnInit {

  errorMessage = '';
  error: string;
  alertmessage = false;


  constructor(public fb: FormBuilder, private loginService: LoginService) {
  }

  public userForm = this.fb.group({
    username: ['', Validators.email],
    password: ['', ]
  });

  ngOnInit() {
    this.alertmessage = false;
  }
  public savePlayer(event) {

    const username = this.userForm.controls[`username`].value;
    const password = this.userForm.controls[`password`].value;

    this.errorMessage = '';

    this.loginService.savePlayer(username, sha1(password)).subscribe(
      test => {
        this.alertmessage = true;
        this.errorMessage = 'Uw account is aangemaakt';
      },

      err => {
        this.alertmessage = true;
        this.errorMessage = 'Het is helaas niet gelukt om een account aan te maken. Probeer het nog eens';
      }
    );

  }

  resetFlags() {
    this.alertmessage = false;
  }
}
