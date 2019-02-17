import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {


  @Input()
  userformComponent: UserFormComponent;

  constructor(public fb: FormBuilder) {
  }

  public userForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  ngOnInit() {
  }
  public saveTodo(event) {

    const username = this.userForm.controls['username'].value;
    const password = this.userForm.controls['password'].value;

    this.todoService.saveUser(new Todo(0, task, description)).subscribe(
      () => this.todoList.getAllTodos()
    );



  }
}
