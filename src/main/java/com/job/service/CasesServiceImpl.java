package com.job.service;

import com.job.exceptions.EntityNotFoundException;
import com.job.repository.CasesRepository;
import com.job.resource.Case;
import com.job.resource.Note;
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
  private final NotesService notesService;

  @Override
  public Case getById(final Long caseId) {
    return casesRepository.findById(caseId)
            .orElseThrow(() -> new EntityNotFoundException(Case.class, caseId));
  }

  @Override
  @Transactional
  public Case saveCase(Case caseEntity) {
    User user = usersService.getUser(caseEntity.getUser());
    caseEntity.setUser(user);
    final Case savedCase = casesRepository.save(caseEntity);
    List<Note> notes = caseEntity.getNotes()
            .stream()
            .map(note -> {
              note.setCaseId(savedCase.getCaseId());
              return note;
            })
            .toList();
    notesService.saveNotes(notes);
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
