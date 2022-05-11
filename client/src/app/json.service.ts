import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JsonService {

  constructor(private httpClient: HttpClient) { }

  getJSONData(file: any): Observable<any> {
    return this.httpClient.get(`${environment.API_ENDPOINT}/uploads/${file}`);
  }
}
