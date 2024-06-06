package pl.mkan.persistance.database.model.mapper;

import org.springframework.stereotype.Component;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;

import java.time.LocalDateTime;

@Component
public class CurrencyRequestMapper {

    public HistoricalCurrencyRate toDTO(CurrencyRequestEntity entity) {
        return new HistoricalCurrencyRate(
                entity.getCurrencyCode(),
                entity.getFetcherName(),
                entity.getFetchDate(),
                entity.getCurrencyValue()
        );
    }

    public CurrencyRequestEntity toEntity(CurrencyRateRequest request, LocalDateTime fetchTime, double currencyValue) {
        return new CurrencyRequestEntity(request.currency(), request.name(), fetchTime, currencyValue);
    }
}
