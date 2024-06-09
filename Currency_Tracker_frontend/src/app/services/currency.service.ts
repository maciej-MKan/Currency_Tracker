import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../enviroments/enviroment';
import { Request } from '../models/request.model';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCurrentCurrencyValue(currency: string, name: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/get-current-currency-value-command`, { currency, name });
  }

  getAllRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(`${this.apiUrl}/requests`);
  }
}
