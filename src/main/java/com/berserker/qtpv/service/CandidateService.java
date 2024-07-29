package com.berserker.qtpv.service;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.model.candidate.CandidateWithResumeDTO;
import com.berserker.qtpv.model.candidate.CreateCandidateDTO;
import com.berserker.qtpv.model.candidate.SearchCandidateDTO;
import org.springframework.data.domain.Page;

public interface CandidateService {
  Candidate createCandidate(CreateCandidateDTO createCandidateDTO);

  String deleteCandidateById(Long id) throws Exception;

  Page<Candidate> search(SearchCandidateDTO searchCandidateDTO);

  CandidateWithResumeDTO findByIdWithResume(Long id);
}
