package com.job.service;

import com.job.repository.CasesRepository;
import com.job.resource.Case;
import com.job.resource.Note;
import com.job.resource.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CasesServiceUnitTest {
  @Mock
  private CasesRepository casesRepository;
  @Mock
  private UsersService usersService;
  @Mock
  private NotesService notesService;

  @InjectMocks
  private CasesServiceImpl casesService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void should_storeNewCase() {
    // given
    User user = User.builder()
            .firstName("John")
            .lastName("Doe")
            .email("test@email.com")
            .build();
    List<Note> notes = List.of(Note.builder().details("details").build());
    Case _case = Case.builder()
            .title("Case title")
            .description("Case description")
            .severity(5)
            .user(user)
            .status(Case.Status.OPEN)
            .notes(notes).build();
    when(casesRepository.save(any(Case.class))).thenReturn(Case.builder().caseId(1L).build());

    // when
    casesService.saveCase(_case);

    //then
    verify(usersService).getUser(user);
    verify(casesRepository).save(_case);
    verify(notesService).saveNotes(notes);
  }
}
