package pl.zajavka.project_manager.integration.configuration;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import({PersistenceContainerTestConfiguration.class, TestDatabaseConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Disabled
public abstract class AbstractIT {

    @LocalServerPort
    protected int port;
}
