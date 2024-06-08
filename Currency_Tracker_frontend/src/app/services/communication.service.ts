import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {
  private refreshRequestsSubject = new Subject<void>();

  get refreshRequests$() {
    return this.refreshRequestsSubject.asObservable();
  }

  emitRefreshRequests() {
    this.refreshRequestsSubject.next();
  }
}
