package com.berserker.qtpv.service;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.model.CreateCandidateDTO;

public interface CandidateService {
  Candidate createCandidate(CreateCandidateDTO createCandidateDTO);

  String deleteCandidateById(Long id) throws Exception;
}
