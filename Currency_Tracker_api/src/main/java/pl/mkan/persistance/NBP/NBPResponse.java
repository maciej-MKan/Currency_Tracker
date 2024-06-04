package pl.mkan.persistance.NBP;

import java.util.List;

public record NBPResponse(String table, String currency, String code, List<Rate> rates) {
    public record Rate(String no, String effectiveDate, double mid) {
    }
}
