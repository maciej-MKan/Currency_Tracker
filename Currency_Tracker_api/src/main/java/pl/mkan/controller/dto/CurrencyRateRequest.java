package pl.mkan.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public record CurrencyRateRequest(@NotEmpty String currency, @NotEmpty String name) {
}
