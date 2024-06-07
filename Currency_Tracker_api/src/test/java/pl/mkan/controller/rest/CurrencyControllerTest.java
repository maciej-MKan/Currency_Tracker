package pl.mkan.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import pl.mkan.controller.dto.CurrencyRate;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.service.CurrencyProvider;
import pl.mkan.service.RequestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CurrencyControllerTest {

    @Mock
    private CurrencyProvider currencyProvider;

    @Mock
    private RequestService requestService;

    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentCurrencyValue() {
        CurrencyRateRequest request = new CurrencyRateRequest("EUR", "John Doe");
        CurrencyRate rate = new CurrencyRate(4.2923);

        when(currencyProvider.fetchCurrencyRate(request.currency()))
                .thenReturn(new CurrencyRate(rate.value()));

        ResponseEntity<CurrencyRate> response = currencyController.getCurrentCurrencyValue(request);

        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(4.2923, response.getBody().value());
    }

    @Test
    void testGetAllRequests() {
        HistoricalCurrencyRate entity1 = new HistoricalCurrencyRate(
                "EUR",
                "John Doe",
                LocalDateTime.now(),
                4.2923
        );

        HistoricalCurrencyRate entity2 = new HistoricalCurrencyRate(
                "USD",
                "Jane Doe",
                LocalDateTime.now(),
                3.7123
        );

        when(requestService.getAll()).thenReturn(List.of(entity1, entity2));

        ResponseEntity<List<HistoricalCurrencyRate>> response = currencyController.getAllRequests();

        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals("EUR", response.getBody().get(0).currency());
        assertEquals("USD", response.getBody().get(1).currency());
    }
}
