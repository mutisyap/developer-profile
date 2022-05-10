import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  constructor(private httpClient: HttpClient) { }

  createArticle(article: any): Observable<any> {
    return this.httpClient.post(`${environment.API_ENDPOINT}/api/blogs`, article);
  }

  getArticles(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${environment.API_ENDPOINT}/api/blogs`);
  }

  getArticle(id: number): Observable<any> {
    return this.httpClient.get<any>(`${environment.API_ENDPOINT}/api/blogs/${id}`);
  }

  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    const req = new HttpRequest('POST', `${environment.API_ENDPOINT}/api/upload`, formData, {
      reportProgress: true
    });

    return this.httpClient.request(req);
  }
}
