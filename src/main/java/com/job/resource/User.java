package com.job.resource;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@MappedEntity("users")
public class User {
  @Id
  @GeneratedValue
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;
}
