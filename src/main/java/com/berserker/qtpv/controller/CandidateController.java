package com.berserker.qtpv.controller;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.model.candidate.CandidateWithResumeDTO;
import com.berserker.qtpv.model.candidate.CreateCandidateDTO;
import com.berserker.qtpv.model.candidate.SearchCandidateDTO;
import com.berserker.qtpv.service.CandidateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  @GetMapping("/search")
  ResponseEntity<Page<Candidate>> search(SearchCandidateDTO searchCandidateDTO) throws Exception {
    try {
      return ResponseEntity.ok(candidateService.search(searchCandidateDTO));
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  CandidateWithResumeDTO findById(@PathVariable Long id) {
    return candidateService.findByIdWithResume(id);
  }
  @PostMapping()
  Candidate createCandidate(@RequestBody CreateCandidateDTO createCandidateDTO) {
    Candidate createdCandidate = candidateService.createCandidate(createCandidateDTO);
    return createdCandidate;
  }

  @DeleteMapping()
  String deleteCandidate(@RequestParam("id") Long id) throws Exception {
    String result = candidateService.deleteCandidateById(id);
    return result;
  }
}
