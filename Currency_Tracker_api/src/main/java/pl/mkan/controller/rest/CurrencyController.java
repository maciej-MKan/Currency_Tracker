package pl.mkan.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkan.controller.dto.CurrencyRequestDTO;
import pl.mkan.controller.dto.CurrencyResponseDTO;
import pl.mkan.controller.dto.RequestDTO;
import pl.mkan.service.CurrencyService;
import pl.mkan.service.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = CurrencyController.API_PATH)
@AllArgsConstructor
public class CurrencyController {

    public static final String API_PATH = "/currencies";
    private final CurrencyService currencyService;
    private final RequestService requestService;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyResponseDTO> getCurrentCurrencyValue(@RequestBody CurrencyRequestDTO request) {
        requestService.saveRequest(request);
        return ResponseEntity.ok(currencyService.getValue(request));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<RequestDTO>> getAllRequests(){
        return requestService.getAll();
    }
}
