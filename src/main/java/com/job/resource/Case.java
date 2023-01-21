package com.job.resource;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@MappedEntity("cases")
public class Case {
  @Id
  @GeneratedValue
  private Long caseId;
  private String title;
  private String description;
  private Integer severity;
  private Status status;
  @Relation(Relation.Kind.MANY_TO_ONE)
  private User user;

  // This relation doesn't work as expected with Micronaut JDBC, I didn't figure it out in time
  @Relation(Relation.Kind.ONE_TO_MANY)
  private List<Note> notes;


  public enum Status {
    OPEN,
    CLOSED
  }
}
