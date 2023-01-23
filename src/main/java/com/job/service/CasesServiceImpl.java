package com.job.service;

import com.job.exceptions.EntityNotFoundException;
import com.job.repository.CasesRepository;
import com.job.resource.Case;
import com.job.resource.User;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@Singleton
@RequiredArgsConstructor
class CasesServiceImpl implements CasesService {
  private final CasesRepository casesRepository;
  private final UsersService usersService;

  @Override
  public Case getById(final Long caseId) {
    return casesRepository.findByCaseId(caseId)
            .orElseThrow(() -> new EntityNotFoundException(Case.class, caseId));
  }

  @Override
  @Transactional
  public Case saveCase(Case caseEntity) {
    User user = usersService.getUser(caseEntity.getUser());
    caseEntity.setUser(user);
    caseEntity.getNotes().forEach(note -> note.set_case(caseEntity));
    final Case savedCase = casesRepository.save(caseEntity);
    return savedCase;
  }

  @Override
  public List<Case> getCasesByStatus(final Case.Status status) {
    return casesRepository.findByStatus(status);
  }

  @Override
  public List<Case> getUsersOpenCases(Long userId) {
    return casesRepository.findOpenByUserUserId(userId);
  }

  @Override
  public List<Case> getUsersCasesByStatus(Long userId, Case.Status status) {
    return casesRepository.getUsersCasesByStatus(userId, status);
  }
}
