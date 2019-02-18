import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {LoginService} from '../login.service';
import {Player} from '../../Player';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
  providers: [LoginService]
})
export class UserFormComponent implements OnInit {



  constructor(public fb: FormBuilder, private loginService: LoginService) {
  }

  public userForm = this.fb.group({
    username: ['', ],
    password: ['', ]
  });

  ngOnInit() {
  }
  public savePlayer(event) {

    const username = this.userForm.controls[`username`].value;
    const password = this.userForm.controls[`password`].value;

    this.loginService.savePlayer(username, password).subscribe();

  }
}
