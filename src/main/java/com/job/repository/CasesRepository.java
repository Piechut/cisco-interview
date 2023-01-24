package com.job.repository;

import com.job.resource.Case;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface CasesRepository extends CrudRepository<Case, Long>, JpaSpecificationExecutor<Case> {

  Optional<Case> findByCaseId(@NotNull Long aLong);

  List<Case> findByStatus(Case.Status status);

  @Query("""
          SELECT c
          FROM Case c
          WHERE c.user.userId = :userId
          AND c.status = 'OPEN'
          """)
  List<Case> findOpenByUserUserId(Long userId);


  @Query("""
          SELECT c
          FROM Case c
          WHERE c.user.userId = :userId
          AND c.status = :status
          """)
  List<Case> getUsersCasesByStatus(Long userId, Case.Status status);
}
