package pl.mkan.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.CurrencyRequestDTO;
import pl.mkan.controller.dto.RequestDTO;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;
import pl.mkan.persistance.database.model.mapper.CurrencyRequestMapper;
import pl.mkan.persistance.database.repository.CurrencyRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class RequestService {
    private CurrencyRequestRepository repository;
    private CurrencyRequestMapper mapper;

    public void saveRequest(CurrencyRequestDTO request, double value) {
        repository.save(CurrencyRequestEntity.builder()
                .name(request.name())
                .currency(request.currency())
                .date(LocalDateTime.now())
                .mid(value)
                .build()
        );
    }

    public List<RequestDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}
