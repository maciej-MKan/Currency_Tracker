package pl.mkan.client.NBP;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.mkan.client.NBP.exception.CurrencyNotFoundException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class NBPCurrencyClient {

    private final WebClient webClient;
    @Value("${nbp-api.currency.uri}")
    private String currencyUri;

    public Double getCurrencyValue(String currencyCode) {
        NBPResponse response = webClient
                .get()
                .uri(currencyUri + currencyCode + "?format=json")
                .retrieve()
                .onStatus((HttpStatusCode::is4xxClientError), clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(
                                        new CurrencyNotFoundException("Currency not found: " + currencyCode)
                                ));
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .bodyToMono(NBPResponse.class)
                .block();

        if (response != null && response.rates() != null && !response.rates().isEmpty()) {
            return response.rates().get(0).mid();
        } else {
            throw new IllegalArgumentException("Invalid response from NBP API");
        }
    }
}
