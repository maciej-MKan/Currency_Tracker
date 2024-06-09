package pl.mkan.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.mkan.controller.dto.CurrencyRate;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.ExceptionMessage;
import pl.mkan.controller.rest.CurrencyController;
import pl.mkan.integration.support.RestAssuredITBase;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiClientIT
        extends RestAssuredITBase {

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(
                options().globalTemplating(false).port(9999)
        );
        wireMockServer.start();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @Test
    void thatCurrentValueCanBeFetchedCorrectly() {
        //given
        double currencyValue = 4.2923;
        wireMockServer.stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("stub/currency-rate.json")
                        .withTransformerParameter("value", currencyValue)
                        .withTransformers("response-template")
                ));
        CurrencyRateRequest request = new CurrencyRateRequest("EUR", "Bolek");

        //when
        CurrencyRate response = requestSpecification()
                .body(request)
                .post(CurrencyController.API_PATH + CurrencyController.CURRENCY_VALUE_PATH)
                .then()
                .statusCode(200)
                .extract()
                .as(CurrencyRate.class);

        //then
        assertThat(response)
                .isNotNull();

        assertThat(response.value())
                .isEqualTo(currencyValue);
    }

    @Test
    void thatCurrencyCodeNonExist() {
        //given
        String currencyCode = "INVALID";
        wireMockServer.stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(404)
                        .withBody("404 NotFound")
                ));
        CurrencyRateRequest request = new CurrencyRateRequest(currencyCode, "Bolek");

        //when
        ExceptionMessage response = requestSpecification()
                .body(request)
                .post(CurrencyController.API_PATH + CurrencyController.CURRENCY_VALUE_PATH)
                .then()
                .statusCode(404)
                .extract()
                .as(ExceptionMessage.class);

        //then
        assertThat(response).isNotNull();
        assertThat(response.message()).contains(currencyCode);
    }
}
