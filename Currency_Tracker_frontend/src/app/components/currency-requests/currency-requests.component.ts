import { Component, OnInit } from '@angular/core';
import { CurrencyService } from '../../services/currency.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-currency-requests',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './currency-requests.component.html',
  styleUrls: ['./currency-requests.component.css']
})
export class CurrencyRequestsComponent implements OnInit {
  requests: any[] = [];

  constructor(private currencyService: CurrencyService) { }

  ngOnInit(): void {
    this.currencyService.getAllRequests().subscribe(
       (data) => this.requests = data,
      (error) => alert('Wystąpił błąd: ' + error.message),
      () => console.info('complete')
    );
  }
}
