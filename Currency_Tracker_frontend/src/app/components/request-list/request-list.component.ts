import { Component, OnDestroy, OnInit } from '@angular/core';
import { CurrencyService } from "../../services/currency.service";
import { DatePipe, NgForOf, UpperCasePipe } from "@angular/common";
import { Request } from '../../models/request.model';
import { CommunicationService } from "../../services/communication.service";
import { Subscription } from "rxjs";

@Component({
  selector: 'app-request-list',
  standalone: true,
  imports: [
    NgForOf,
    DatePipe,
    UpperCasePipe
  ],
  templateUrl: './request-list.component.html',
  styleUrl: './request-list.component.css'
})
export class RequestListComponent implements OnInit, OnDestroy{
  requests: Request[] = [];
  filteredRequests: Request[] = [];
  filter: string = 'all';
  private subscription = new Subscription();

  constructor(private currencyService: CurrencyService, private communicationService: CommunicationService) { }

  ngOnInit(): void {
    this.fetchRequests();

    // Subscribe to refresh events
    this.subscription.add(
      this.communicationService.refreshRequests$.subscribe(() => {
        this.fetchRequests();
      })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  fetchRequests(): void {
    this.currencyService.getAllRequests().subscribe((data: Request[]) => {
      this.requests = data.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      this.applyFilter();
    });
  }

  applyFilter(): void {
    const now = new Date();
    switch (this.filter) {
      case 'hour':
        this.filteredRequests = this.requests.filter(req => new Date(req.date) >= new Date(now.getTime() - 60 * 60 * 1000));
        break;
      case 'day':
        this.filteredRequests = this.requests.filter(req => new Date(req.date) >= new Date(now.getTime() - 24 * 60 * 60 * 1000));
        break;
      case 'week':
        this.filteredRequests = this.requests.filter(req => new Date(req.date) >= new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000));
        break;
      default:
        this.filteredRequests = this.requests;
        break;
    }
  }

  setFilter(filter: string): void {
    this.filter = filter;
    this.applyFilter();
  }
}
