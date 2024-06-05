package pl.mkan.controller.dto;

import java.time.LocalDateTime;

public record HistoricalCurrencyRate(String currency, String name, LocalDateTime date, double value) {
}
