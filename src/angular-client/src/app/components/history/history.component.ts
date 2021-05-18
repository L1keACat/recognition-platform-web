import {Component, OnInit} from '@angular/core';
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {Router} from "@angular/router";
import {faCheckCircle, faExclamationCircle} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  faCheckCircle = faCheckCircle;
  faExclamationCircle = faExclamationCircle;

  user: any = this.dataService.getUser();
  history: any;

  constructor(private dataService: DataService,
              private httpService: HttpService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (!this.user) {
      this.router.navigate(['/login']);
    }
    this.httpService.getHistory(this.user).subscribe(data => this.history = data);
    console.log(this.history);
  }

  redirect(data: string): void {
    this.dataService.setData(data);
    this.router.navigate(['/result']);
  }
}
