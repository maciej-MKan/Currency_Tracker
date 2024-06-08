package pl.mkan.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkan.controller.dto.CurrencyRate;
import pl.mkan.controller.dto.CurrencyRateRequest;
import pl.mkan.controller.dto.HistoricalCurrencyRate;
import pl.mkan.service.CurrencyProvider;
import pl.mkan.service.RequestService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = CurrencyController.API_PATH)
@AllArgsConstructor
public class CurrencyController {

    public static final String API_PATH = "/currencies";

    private final CurrencyProvider currencyProvider;
    private final RequestService requestService;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyRate> getCurrentCurrencyValue(@Valid @RequestBody CurrencyRateRequest request) {
        log.info("Get current currency value request: {}", request);
        CurrencyRate responseDTO = currencyProvider.fetchCurrencyRate(request.currency());
        requestService.saveRequest(request, responseDTO.value());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<HistoricalCurrencyRate>> getAllRequests() {
        log.info("Get current requests");
        return ResponseEntity.ok(requestService.getAll());
    }
}
