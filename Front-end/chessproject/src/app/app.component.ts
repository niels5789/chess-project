import { Component, OnInit } from '@angular/core';
import {LocalStorageService} from './local-storage.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  constructor(private router: Router, private storage: LocalStorageService) {

  }

  ngOnInit() {
    if (this.storage.getStoredUser() == null) {
      this.router.navigateByUrl('');
    }
  }

}
