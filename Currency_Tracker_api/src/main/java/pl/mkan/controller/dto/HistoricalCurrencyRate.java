package pl.mkan.controller.dto;

import java.time.ZonedDateTime;

public record HistoricalCurrencyRate(String currency, String name, ZonedDateTime date, double value) {
}
