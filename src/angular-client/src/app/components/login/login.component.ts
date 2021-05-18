import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email?: string;

  constructor(
    private router: Router,
    private dataService: DataService) {
  }

  ngOnInit(): void {
    if (this.dataService.getUser()) {
      this.router.navigate(['/decodeFile']);
    }
  }

  connect(email: string) {
    this.dataService.setUser(email);
    this.router.navigate(['/decodeFile']);
  }

}
