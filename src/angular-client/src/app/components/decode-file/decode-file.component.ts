import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpService} from "../../services/http.service";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-decode-file',
  templateUrl: './decode-file.component.html',
  styleUrls: ['./decode-file.component.css']
})
export class DecodeFileComponent implements OnInit {

  selectedFiles: FileList | undefined;
  currentFile: File | undefined;
  message: string | undefined;
  user: any = this.dataService.getUser();

  constructor(
    private httpService: HttpService,
    private dataService: DataService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.user) {
      this.router.navigate(['/login']);
    }
  }

  selectFile(event: Event): void {
    this.selectedFiles = (<HTMLInputElement>event.target).files!;
  }

  upload(): void {
    let response;

    this.currentFile = this.selectedFiles!.item(0)!;
    this.httpService.upload(this.currentFile, this.user).subscribe(
      event => {
        response = event;
        if (response == null)
          this.message = 'Could not find code on the image!';
        else {
          this.dataService.setData(response);
          this.router.navigate([`/result`]);
        }
      },
      err => {
        console.log(err);
        this.message = 'Can\'t find any code on the image!';
        this.currentFile = undefined;
      });
    this.selectedFiles = undefined;
  }

}
