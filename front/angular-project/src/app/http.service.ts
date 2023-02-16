import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private url: string = "https://jsonplaceholder.typicode.com/todos/";

  constructor(private http: HttpClient) { }

  getRequest(id: number): Observable<any>{
    return this.http.get(this.url + id);
  }
}
