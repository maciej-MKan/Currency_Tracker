package pl.mkan.controller.dto;

import java.time.LocalDateTime;

public record RequestDTO(String currency, String name, LocalDateTime date, double value) {
}
