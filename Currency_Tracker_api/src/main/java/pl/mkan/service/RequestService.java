package pl.mkan.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.CurrencyRequestDTO;
import pl.mkan.controller.dto.RequestDTO;

import java.util.List;

@Service
public class RequestService {
    public void saveRequest(CurrencyRequestDTO request) {

    }

    public ResponseEntity<List<RequestDTO>> getAll() {
        return null;
    }
}
