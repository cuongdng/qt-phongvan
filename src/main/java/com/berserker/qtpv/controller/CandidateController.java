package com.berserker.qtpv.controller;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.model.CreateCandidateDTO;
import com.berserker.qtpv.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("candidate")
@RequiredArgsConstructor
public class CandidateController {
  private final CandidateService candidateService;
  @PostMapping()
  Candidate createCandidate(@RequestBody CreateCandidateDTO createCandidateDTO) {
    Candidate createdCandidate = candidateService.createCandidate(createCandidateDTO);
    return createdCandidate;
  }

  @DeleteMapping()
  String deleteCandidate(@RequestParam("id") Long id) {
    String result = candidateService.deleteCandidateById(id);
    return result;
  }
}
