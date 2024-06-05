package pl.mkan.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkan.client.NBP.NBPCurrencyClient;
import pl.mkan.controller.dto.CurrencyRate;

@AllArgsConstructor
@Service
public class NBPCurrencyProvider implements CurrencyProvider{

    private final NBPCurrencyClient nbpClient;

    @Override
    public CurrencyRate fetchCurrencyRate(String currencyCode) {
        double value = nbpClient.getCurrencyValue(currencyCode);

        return new CurrencyRate(value);
    }
}
