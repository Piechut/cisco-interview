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
@MappedEntity("notes")
public class Note {
  @Id
  @GeneratedValue
  private Long noteId;
  private Long caseId;
  private String details;

}
