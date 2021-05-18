import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DecodeFileComponent} from "./components/decode-file/decode-file.component";
import {ProductDetailsComponent} from "./components/product-details/product-details.component";
import {LoginComponent} from "./components/login/login.component";
import {HistoryComponent} from "./components/history/history.component";

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'decodeFile', component: DecodeFileComponent },
  { path: 'result', component: ProductDetailsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'history', component: HistoryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
