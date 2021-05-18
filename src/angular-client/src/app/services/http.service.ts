import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const baseUrl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) {
  }

  get(code: any, user: any): Observable<String> {
    return this.http.get<String>(`${baseUrl}/decode/${user}/${code}`);
  }

  upload(file: File, user: any): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(`${baseUrl}/uploadFile/${user}`, formData, {reportProgress: true, responseType: 'json'});
  }

  getHistory(user: any): Observable<String> {
    return this.http.get<String>(`${baseUrl}/history/${user}`);
  }
}
