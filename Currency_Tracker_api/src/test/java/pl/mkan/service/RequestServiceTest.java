package pl.mkan.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;
import pl.mkan.persistance.database.model.mapper.CurrencyRequestMapper;
import pl.mkan.persistance.database.repository.CurrencyRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @Mock
    private CurrencyRequestRepository currencyRequestRepository;

    @Mock
    private CurrencyRequestMapper currencyRequestMapper;

    @InjectMocks
    private RequestService requestService;

    @Test
    void testSaveRequest() {
        CurrencyRequestEntity entity = new CurrencyRequestEntity(
                "EUR",
                "John Doe",
                LocalDateTime.now(),
                4.2923);

        when(currencyRequestMapper.toEntity(
                any(CurrencyRateRequest.class),
                any(LocalDateTime.class),
                anyDouble())
        ).thenCallRealMethod();

        when(currencyRequestRepository.save(any(CurrencyRequestEntity.class))).thenReturn(entity);

        CurrencyRateRequest request = new CurrencyRateRequest("EUR", "John Doe");
        CurrencyRequestEntity result = requestService.saveRequest(request, 4.2923);

        assertNotNull(result);
        assertEquals("EUR", result.getCurrencyCode());
        assertEquals("John Doe", result.getFetcherName());
        assertEquals(4.2923, result.getCurrencyValue());
    }

    @Test
    void testGetAllRequests() {
        CurrencyRequestEntity entity1 = new CurrencyRequestEntity(
                "EUR",
                "John Doe",
                LocalDateTime.now(),
                4.2923
        );

        CurrencyRequestEntity entity2 = new CurrencyRequestEntity(
                "USD",
                "Jane Doe",
                LocalDateTime.now(),
                3.7123
        );

        when(currencyRequestRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(currencyRequestMapper.toDTO(any())).thenCallRealMethod();

        List<HistoricalCurrencyRate> result = requestService.getAll();

        assertEquals(2, result.size());
        assertEquals("EUR", result.get(0).currency());
        assertEquals("USD", result.get(1).currency());
    }
}
