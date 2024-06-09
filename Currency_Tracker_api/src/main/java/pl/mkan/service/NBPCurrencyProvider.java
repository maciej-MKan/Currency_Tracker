package pl.mkan.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.client.NBP.NBPCurrencyClient;
import pl.mkan.controller.dto.CurrencyRate;

@Slf4j
@AllArgsConstructor
@Service
public class NBPCurrencyProvider implements CurrencyProvider {

    private final NBPCurrencyClient nbpClient;

    @Override
    public CurrencyRate fetchCurrencyRate(String currencyCode) {
        log.info("Fetching current currencyValue for currencyCode: {}", currencyCode);

        double value = nbpClient.getCurrencyValue(currencyCode);

        return new CurrencyRate(value);
    }
}
