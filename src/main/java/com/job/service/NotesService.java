package com.job.service;

import com.job.resource.Note;

import java.util.List;

public interface NotesService {
  List<Note> saveNotes(List<Note> notes);

  Note saveNote(Note note);
}
