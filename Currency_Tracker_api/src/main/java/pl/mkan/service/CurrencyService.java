package pl.mkan.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkan.client.NBP.NBPCurrencyClient;
import pl.mkan.controller.dto.CurrencyRequestDTO;
import pl.mkan.controller.dto.CurrencyResponseDTO;

@AllArgsConstructor
@Service
public class CurrencyService {

    private NBPCurrencyClient nbpClient;

    public CurrencyResponseDTO getValue(CurrencyRequestDTO request) {
        double value = nbpClient.getCurrencyValue(request.currency());

        return new CurrencyResponseDTO(value);
    }
}
