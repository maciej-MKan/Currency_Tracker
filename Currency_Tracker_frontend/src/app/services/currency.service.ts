import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private apiUrl = 'http://localhost:8080/currencies'; // URL do twojego backendu

  constructor(private http: HttpClient) { }

  getCurrentCurrencyValue(currency: string, name: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/get-current-currency-value-command`, { currency, name });
  }

  getAllRequests(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/requests`);
  }
}
