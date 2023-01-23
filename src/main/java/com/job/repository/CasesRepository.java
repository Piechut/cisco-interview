package com.job.repository;

import com.job.resource.Case;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface CasesRepository extends CrudRepository<Case, Long>, JpaSpecificationExecutor<Case> {

  @Join(value = "user", alias = "u_", type = Join.Type.FETCH)
  @Join(value = "notes", alias = "n_", type = Join.Type.LEFT_FETCH)
  Optional<Case> findByCaseId(@NotNull Long aLong);

  @Join(value = "user", alias = "u_", type = Join.Type.FETCH)
  @Join(value = "notes", alias = "n_", type = Join.Type.LEFT_FETCH)
  List<Case> findByStatus(Case.Status status);

  @Query("""
          SELECT c
          FROM Case c
          WHERE c.user.userId = :userId
          AND c.status = 'OPEN'
          """)
  @Join(value = "user", alias = "u_", type = Join.Type.FETCH)
  @Join(value = "notes", alias = "n_", type = Join.Type.LEFT_FETCH)
  List<Case> findOpenByUserUserId(Long userId);


  @Query("""
          SELECT c
          FROM Case c
          WHERE c.user.userId = :userId
          AND c.status = :status
          """)
  @Join(value = "user", alias = "u_", type = Join.Type.FETCH)
  @Join(value = "notes", alias = "n_", type = Join.Type.LEFT_FETCH)
  List<Case> getUsersCasesByStatus(Long userId, Case.Status status);
}
