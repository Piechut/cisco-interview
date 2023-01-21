package com.job.service;

import com.job.PostgresqlContainer;
import com.job.resource.Case;
import com.job.resource.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.Test;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class CasesServiceIntegrationTest implements PostgresqlContainer {

  @Inject
  private CasesService casesService;

  @Test
  void should_storeCase_when_newUser() {
    User user = User.builder()
            .userId(1L)
            .firstName("John")
            .lastName("Doe")
            .email("valid@email.com")
            .build();
    Case caseEntity = new Case(1L, "test", "desc", 5, Case.Status.OPEN, user, emptyList());
    Case savedCase = casesService.saveCase(caseEntity);

    assertEquals(caseEntity.getCaseId(), savedCase.getCaseId());
  }
}
