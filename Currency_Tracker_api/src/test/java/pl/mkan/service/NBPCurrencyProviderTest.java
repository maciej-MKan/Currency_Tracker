package pl.mkan.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mkan.client.NBP.NBPCurrencyClient;
import pl.mkan.client.NBP.NBPResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NBPCurrencyProviderTest {

    @Mock
    private NBPCurrencyClient nbpClient;

    @InjectMocks
    private NBPCurrencyProvider nbpCurrencyProvider;

    @Test
    void testGetCurrencyValue_Success() {

        String currencyCode = "EUR";
        List<NBPResponse.Rate> rates = List.of(
                new NBPResponse.Rate("107/A/NBP/2024", "2024-06-04", 4.2923)
        );
        NBPResponse response = new NBPResponse("A", "euro", currencyCode, rates);

        when(nbpClient.getCurrencyValue(currencyCode)).thenReturn(response.rates().get(0).mid());

        double result = nbpCurrencyProvider.fetchCurrencyRate("EUR").value();

        assertEquals(4.2923, result);
    }

}
