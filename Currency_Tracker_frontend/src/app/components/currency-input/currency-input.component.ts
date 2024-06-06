import { Component } from '@angular/core';
import { CurrencyService } from '../../services/currency.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-currency-input',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './currency-input.component.html',
  styleUrls: ['./currency-input.component.css']
})
export class CurrencyInputComponent {
  currency: string = '';
  name: string = '';
  result: any;

  constructor(private currencyService: CurrencyService) { }

  getCurrencyValue() {
    this.currencyService.getCurrentCurrencyValue(this.currency, this.name).subscribe(
      data => this.result = data,
      error => alert('Wystąpił błąd: ' + error.message)
    );
  }
}
