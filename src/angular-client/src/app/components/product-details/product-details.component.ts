import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../services/http.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  data?: any = this.dataService.getData();
  product?: any;
  loaded: boolean = false;
  user: any = this.dataService.getUser();

  constructor(
    private httpService: HttpService,
    private dataService: DataService,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.user) {
      this.router.navigate(['/login']);
    }
    if (this.data) {
      if (this.data['type_dimension'] == "1D") {
        this.getProduct(this.data['content']).then(r => this.loaded = true);
      }
      if (this.data['type_dimension'] == "2D") {
        this.loaded = true;
      }
    }
  }

  async getProduct(code: any): Promise<void | String> {
    return await this.httpService.get(code, this.user).toPromise().then(r => {
      console.log(r);
      // @ts-ignore
      this.product = r['products'][0];
    });
    /*this.productService.get(code).subscribe(
        data => this.product = data,
        error => {
          console.log(error);
        });*/
  }
}
