package pl.mkan.persistance.database.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Override
    public String toString() {
        return "CurrencyRequestEntity{" +
                "id=" + id +
                ", currencyCode='" + currencyCode + '\'' +
                ", fetcherName='" + fetcherName + '\'' +
                ", fetchDate=" + fetchDate +
                ", currencyValue=" + currencyValue +
                '}';
    }
}
