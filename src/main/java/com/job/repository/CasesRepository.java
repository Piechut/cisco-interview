package com.job.repository;

import com.job.resource.Case;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@JdbcRepository
public interface CasesRepository extends CrudRepository<Case, Long> {

  @Query("""
          SELECT * FROM cases c
          WHERE status = :status
          """)
  List<Case> findByStatus(Case.Status status);

  @Query("""
          SELECT c.case_id, c.title, c.description, c.severity, c.status
          FROM cases c
          WHERE c.user_id = :userId
          AND c.status = 'OPEN'
          """)
  @Join(value = "user", alias = "u_", type = Join.Type.FETCH)
  List<Case> findOpenByUserUserId(Long userId);


  @Query("""
          SELECT c.case_id, c.title, c.description, c.severity, c.status
          FROM cases c
          WHERE c.user_id = :userId
          AND c.status = :status
          """)
  @Join(value = "user.notes", alias = "u_", type = Join.Type.FETCH)
  List<Case> getUsersCasesByStatus(Long userId, Case.Status status);
}
