package pl.mkan.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;
import pl.mkan.persistance.database.model.mapper.CurrencyRequestMapper;
import pl.mkan.persistance.database.repository.CurrencyRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class RequestService {

    private final CurrencyRequestRepository repository;
    private final CurrencyRequestMapper mapper;

    public CurrencyRequestEntity saveRequest(CurrencyRateRequest request, double currencyRate) {
        log.info("Saving fetched currencyCode currencyValue for request: {}, currencyValue: {}", request, currencyRate);
        return repository.save(mapper.toEntity(request, LocalDateTime.now(), currencyRate));
    }

    public List<HistoricalCurrencyRate> getAll() {
        List<CurrencyRequestEntity> entityList = repository.findAll();

        return entityList.stream()
                .peek(currencyRequestEntity -> log.info("Found saved request: {}", currencyRequestEntity.toString()))
                .map(mapper::toDTO)
                .toList();
    }
}
