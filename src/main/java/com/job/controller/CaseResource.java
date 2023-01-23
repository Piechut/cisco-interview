package com.job.controller;

import com.job.exceptions.EntityNotFoundException;
import com.job.resource.Case;
import com.job.resource.Note;
import com.job.service.CasesService;
import com.job.service.NotesService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@Controller("/cases")
public class CaseResource {

  private final CasesService casesService;
  private final NotesService notesService;

  public CaseResource(final CasesService casesService,
                      final NotesService notesService) {
    this.casesService = casesService;
    this.notesService = notesService;
  }

  @Operation(summary = "Returns cases with given status")
  @Get("/status/{status}")
  HttpResponse<List<Case>> getCasesWithStatus(@PathVariable Case.Status status) {
    return HttpResponse.ok(casesService.getCasesByStatus(status));
  }

  @Operation(summary = "Returns open cases of the user")
  @Get("/user/{userId}")
  HttpResponse<List<Case>> getOpenCases(@PathVariable Long userId) {
    return HttpResponse.ok(casesService.getUsersOpenCases(userId));
  }

  @Operation(summary = "Returns cases with given status of the user")
  @Get("/user/{userId}/status/{status}")
  HttpResponse<List<Case>> getOpenCases(@PathVariable Long userId,
                                        @PathVariable Case.Status status) {
    return HttpResponse.ok(casesService.getUsersCasesByStatus(userId, status));
  }

  @Operation(summary = "Returns cases with given id")
  @Get("/{caseId}")
  HttpResponse<Case> getCase(@PathVariable Long caseId) {
    try {
      return HttpResponse.ok(casesService.getById(caseId));
    } catch (EntityNotFoundException e) {
      return HttpResponse.notFound();
    }
  }

  @Operation(summary = "Creates new case")
  @Post(value = "/create", consumes = "application/json")
  HttpResponse<Case> createCase(@RequestBody Case toCreate) {
    return HttpResponse.created(casesService.saveCase(toCreate));
  }

  @Operation(summary = "Creates new note for case with given id")
  @Post(value = "/{caseId}/addNote", consumes = "text/plain")
  HttpResponse<Note> addNote(@PathVariable Long caseId, @RequestBody String detail) {

    return HttpResponse.created(notesService.saveNote(Note.builder()
            ._case(Case.builder().caseId(caseId).build())
            .details(detail)
            .build()));
  }
}
