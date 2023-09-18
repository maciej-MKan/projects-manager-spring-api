package pl.zajavka.project_manager.integration.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration
public class PersistenceContainerTestConfiguration {

    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";
    private static final String POSTGRESQL = "postgresql";
    private static final String POSTGRESQL_CONTAINER = "postgres:15.0";

    @Bean
    @Qualifier(POSTGRESQL)
    PostgreSQLContainer<?> postgresqlContainer() {
        try(PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>(POSTGRESQL_CONTAINER)
            ) {
            postgresqlContainer
                    .withUsername(USERNAME)
                    .withPassword(PASSWORD)
                    .start();
            return postgresqlContainer;
        }
    }

    @Bean
    DataSource dataSource(final PostgreSQLContainer<?> container) {
        return DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .driverClassName(container.getDriverClassName())
            .url(container.getJdbcUrl())
            .username(container.getUsername())
            .password(container.getPassword())
            .build();
    }
}
