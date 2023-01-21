package com.job.service;

import com.job.repository.NotesRepository;
import com.job.resource.Note;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.StreamSupport;

@Singleton
@RequiredArgsConstructor
class NotesServiceImpl implements NotesService {

  private final NotesRepository notesRepository;

  @Override
  public List<Note> saveNotes(List<Note> notes) {
    return StreamSupport.stream(notesRepository.saveAll(notes).spliterator(), false)
            .toList();
  }

  @Override
  public Note saveNote(Note note) {
    return notesRepository.save(note);
  }
}
