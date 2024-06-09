import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CurrencyInputComponent } from "./components/currency-input/currency-input.component";
import { RequestListComponent } from "./components/request-list/request-list.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CurrencyInputComponent, RequestListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Currency_Tracker_frontend';
}
