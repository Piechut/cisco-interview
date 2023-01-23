package com.job.repository;

import com.job.resource.Note;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface NotesRepository extends CrudRepository<Note, Long>, JpaSpecificationExecutor<Note> {
}
