package com.job.repository;

import com.job.resource.Note;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository
public interface NotesRepository extends CrudRepository<Note, Long> {
}
