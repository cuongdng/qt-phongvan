package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.CreateCandidateDTO;
import com.berserker.qtpv.repository.CandidateRepository;
import com.berserker.qtpv.repository.ResumeRepository;
import com.berserker.qtpv.service.CandidateService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

  private final CandidateRepository candidateRepository;
  private final ResumeRepository resumeRepository;

  @Override
  public Candidate createCandidate(CreateCandidateDTO createCandidateDTO) {
    Optional<Candidate> existCandidate = candidateRepository.findByPhoneNumber(
        createCandidateDTO.getPhoneNumber());
    if (existCandidate.isPresent()) {
      Resume resume = Resume.builder().resumeUrl(createCandidateDTO.getResumeUrl())
          .candidate(existCandidate.get()).build();
      resumeRepository.save(resume);
      return existCandidate.get();
    } else {
      Candidate toCreateCandidate = new Candidate(createCandidateDTO);
      Resume resume = Resume.builder().resumeUrl(createCandidateDTO.getResumeUrl())
          .candidate(toCreateCandidate).build();
      toCreateCandidate.setResumes(List.of(resume));
      return candidateRepository.save(toCreateCandidate);
    }

  }

  @Override
  public String deleteCandidateById(Long id) {
    candidateRepository.deleteById(id);
    return "Delete successfully";
  }
}
