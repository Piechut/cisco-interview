package com.job.service;

import com.job.AbstractIntegrationTest;
import com.job.repository.CasesRepository;
import com.job.resource.Case;
import com.job.resource.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class CasesServiceIntegrationTest extends AbstractIntegrationTest {

  @Inject
  private CasesService casesService;

  @Inject
  private CasesRepository casesRepository;

  @Test
  void should_storeCase_when_newUser() {
    // GIVEN
    User user = User.builder()
            .firstName("John")
            .lastName("Doe")
            .email("valid@email.com")
            .build();
    Case caseEntity = new Case(null, "test", "desc", 5, Case.Status.OPEN, user, emptyList());

    // WHEN
    casesService.saveCase(caseEntity);

    // THEN
    assertEquals(1, casesRepository.findAll().spliterator().estimateSize());
  }
}
