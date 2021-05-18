import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  private data: any;
  private user: any;

  setData(data: string){
    this.data = data;
  }

  getData(){
    return this.data;
  }

  clearData(){
    this.data = undefined;
  }

  setUser(user: string){
    this.user = user;
  }

  getUser(){
    return this.user;
  }
}
