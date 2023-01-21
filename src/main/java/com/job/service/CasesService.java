package com.job.service;

import com.job.resource.Case;

import java.util.List;

public interface CasesService {

  Case getById(Long caseId);

  Case saveCase(Case caseEntity);


  List<Case> getCasesByStatus(Case.Status status);

  List<Case> getUsersOpenCases(final Long userId);

  List<Case> getUsersCasesByStatus(final Long userId, final Case.Status status);

}
