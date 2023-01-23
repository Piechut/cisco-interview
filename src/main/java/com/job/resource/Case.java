package com.job.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "cases")
public class Case {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long caseId;
  private String title;
  private String description;
  private Integer severity;
  @Enumerated(EnumType.STRING)
  private Status status;
  @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "_case")
  private List<Note> notes = new ArrayList<>();

  public Case() {

  }

  public enum Status {
    OPEN,
    CLOSED
  }
}
