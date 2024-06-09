package pl.mkan.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.mkan.client.NBP.exception.CurrencyNotFoundException;
import pl.mkan.controller.dto.CurrencyRate;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.ExceptionMessage;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.controller.rest.CurrencyController;
import pl.mkan.integration.support.RestAssuredITBase;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;
import pl.mkan.service.CurrencyProvider;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CurrencyControllerIT
        extends RestAssuredITBase {

    @MockBean
    private CurrencyProvider currencyProvider;

    @BeforeEach
    void initDB() {
        CurrencyRateRequest request1 = new CurrencyRateRequest("USD", "Bolek");
        CurrencyRateRequest request2 = new CurrencyRateRequest("EUR", "Lolek");

        requestService.saveRequest(request1, 1.23);
        requestService.saveRequest(request2, 2.34);

        List<CurrencyRequestEntity> savedRequests = currencyRequestRepository.findAll();
        assertThat(savedRequests).hasSize(2);
    }

    @Test
    void thatRequestListCanBeRetrievedCorrectly() {
        //given
        List<HistoricalCurrencyRate> expectedResponse = List.of(
                new HistoricalCurrencyRate("USD", "Bolek", null, 1.23),
                new HistoricalCurrencyRate("EUR", "Lolek", null, 2.34)
        );

        //when
        List<HistoricalCurrencyRate> response = requestSpecification()
                .get(CurrencyController.API_PATH + CurrencyController.REQUEST_PATH)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", HistoricalCurrencyRate.class);

        //then
        assertThat(response)
                .allSatisfy(request -> assertThat(request.date()).isNotNull())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("date")
                .containsExactlyInAnyOrderElementsOf(expectedResponse);
    }

    @Test
    void thatCurrencyValueCanBeRetrievedCorrectly() {
        //given
        CurrencyRateRequest request = new CurrencyRateRequest("USD", "Bolek");
        CurrencyRate expectedResponse = new CurrencyRate(4.23);
        when(currencyProvider.fetchCurrencyRate(request.currency())).thenReturn(expectedResponse);

        //when
        CurrencyRate response = requestSpecification()
                .body(request)
                .post(CurrencyController.API_PATH + CurrencyController.CURRENCY_VALUE_PATH)
                .then()
                .statusCode(200)
                .extract()
                .as(CurrencyRate.class);

        //then
        assertThat(response).isEqualTo(expectedResponse);

        List<CurrencyRequestEntity> savedRequests = currencyRequestRepository.findAll();
        assertThat(savedRequests).hasSize(3);

    }

    @Test
    void thatExceptionOfNonExistentCurrency() {
        //given
        CurrencyRateRequest request = new CurrencyRateRequest("INCORRECT", "Bolek");
        String message = "Currency not find";
        when(currencyProvider.fetchCurrencyRate(request.currency()))
                .thenThrow(new CurrencyNotFoundException(message));

        //when
        ExceptionMessage response = requestSpecification()
                .body(request)
                .post(CurrencyController.API_PATH + CurrencyController.CURRENCY_VALUE_PATH)
                .then()
                .statusCode(404)
                .extract()
                .as(ExceptionMessage.class);

        //then
        assertThat(response.message()).contains(message);

    }
}
