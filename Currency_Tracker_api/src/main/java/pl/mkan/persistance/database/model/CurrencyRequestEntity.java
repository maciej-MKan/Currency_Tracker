package pl.mkan.persistance.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class CurrencyRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyCode;
    private String fetcherName;
    private LocalDateTime fetchDate;
    private double currencyValue;

    public CurrencyRequestEntity(String currencyCode, String fetcherName, LocalDateTime fetchDate, double currencyValue) {
        this.currencyCode = currencyCode;
        this.fetcherName = fetcherName;
        this.fetchDate = fetchDate;
        this.currencyValue = currencyValue;
    }
}
