import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyInputComponent } from './currency-input.component';

describe('CurrencyInputComponent', () => {
  let component: CurrencyInputComponent;
  let fixture: ComponentFixture<CurrencyInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurrencyInputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurrencyInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
