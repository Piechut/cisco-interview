package com.job.repository;

import com.job.resource.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
  Optional<User> findByEmail(String email);
}
