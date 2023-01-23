package com.job;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public interface PostgresqlContainer extends TestPropertyProvider {

  PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.4-alpine")
          .withDatabaseName("demo")
          .withUsername("postgres")
          .withPassword("secret");

  @Override
  @NonNull
  default Map<String, String> getProperties() {
    postgres.start();

    return Map.of(
            "datasources.default.url", postgres.getJdbcUrl(),
            "datasources.default.username", postgres.getUsername(),
            "datasources.default.password", postgres.getPassword()
    );
  }
}
