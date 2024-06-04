package pl.mkan.persistance.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;

@Repository
public interface CurrencyRequestRepository extends JpaRepository<CurrencyRequestEntity, Long> {
}
