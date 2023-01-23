package com.job.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "notes")
@EqualsAndHashCode(exclude = "_case")
public class Note {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long noteId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "case_id", referencedColumnName = "caseId", nullable = false)
  @JsonIgnore
  private Case _case;
  private String details;

  public Note() {

  }
}
