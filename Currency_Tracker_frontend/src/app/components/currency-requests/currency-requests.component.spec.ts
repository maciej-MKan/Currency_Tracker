import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyRequestsComponent } from './currency-requests.component';

describe('CurrencyRequestsComponent', () => {
  let component: CurrencyRequestsComponent;
  let fixture: ComponentFixture<CurrencyRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurrencyRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurrencyRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
