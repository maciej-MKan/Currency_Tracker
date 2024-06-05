package pl.mkan.service;

import pl.mkan.controller.dto.CurrencyRate;

public interface CurrencyProvider {

    CurrencyRate fetchCurrencyRate(String currencyCode);

}
