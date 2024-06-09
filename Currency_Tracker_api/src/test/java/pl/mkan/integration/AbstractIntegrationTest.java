package pl.mkan.integration;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import pl.mkan.CurrencyTrackerApp;
import pl.mkan.integration.config.TestContainerConfig;
import pl.mkan.persistance.database.repository.CurrencyRequestRepository;
import pl.mkan.service.CurrencyProvider;
import pl.mkan.service.RequestService;

@ActiveProfiles("test")
@Import(TestContainerConfig.class)
@SpringBootTest(
        classes = {CurrencyTrackerApp.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class AbstractIntegrationTest {

    @Autowired
    protected CurrencyRequestRepository currencyRequestRepository;

    @Autowired
    protected RequestService requestService;

    @Autowired
    protected CurrencyProvider currencyProvider;

    @AfterEach
    public void after() {
        currencyRequestRepository.deleteAll();
    }
}
