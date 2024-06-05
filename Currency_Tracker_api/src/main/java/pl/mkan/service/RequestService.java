package pl.mkan.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.persistance.database.model.mapper.CurrencyRequestMapper;
import pl.mkan.persistance.database.repository.CurrencyRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class RequestService {

    private final CurrencyRequestRepository repository;
    private final CurrencyRequestMapper mapper;

    public void saveRequest(CurrencyRateRequest request, double value) {
        repository.save(mapper.toEntity(request, LocalDateTime.now(), value));
    }

    public List<HistoricalCurrencyRate> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}
