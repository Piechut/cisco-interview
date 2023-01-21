package com.job.repository;

import com.job.resource.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@JdbcRepository
public interface UsersRepository extends CrudRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
