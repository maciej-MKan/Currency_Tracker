package pl.mkan.persistance.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;

public interface CurrencyRequestRepository extends JpaRepository<CurrencyRequestEntity, Long> {
}
