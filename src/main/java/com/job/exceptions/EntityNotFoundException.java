package com.job.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class className, Long id) {
    super(className.getSimpleName() + "with ID: " + id + " was not found.");
  }
}
