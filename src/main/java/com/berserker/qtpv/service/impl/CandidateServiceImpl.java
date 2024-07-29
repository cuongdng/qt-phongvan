package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.base.ResumeStatus;
import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.CustomException;
import com.berserker.qtpv.model.candidate.CandidateWithResumeDTO;
import com.berserker.qtpv.model.candidate.CreateCandidateDTO;
import com.berserker.qtpv.model.candidate.SearchCandidateDTO;
import com.berserker.qtpv.repository.CandidateRepository;
import com.berserker.qtpv.repository.ResumeRepository;
import com.berserker.qtpv.service.CandidateService;
import com.berserker.qtpv.specification.CandidateSpecification;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

  private final CandidateRepository candidateRepository;
  private final ResumeRepository resumeRepository;

  @Override
  @Transactional
  public Candidate createCandidate(CreateCandidateDTO createCandidateDTO) {
    Optional<Candidate> existCandidate = candidateRepository.findByEmail(
        createCandidateDTO.getEmail());
    if (existCandidate.isPresent()) {
      Resume resume = Resume.builder().resumeUrl(createCandidateDTO.getResumeUrl()).status(
              ResumeStatus.IDLE)
          .candidate(existCandidate.get()).build();
      resumeRepository.save(resume);
      return existCandidate.get();
    } else {
      Candidate toCreateCandidate = new Candidate(createCandidateDTO);
      Resume resume = Resume.builder().resumeUrl(createCandidateDTO.getResumeUrl()).status(ResumeStatus.IDLE)
          .candidate(toCreateCandidate).build();
      resumeRepository.save(resume);
      return toCreateCandidate;
    }

  }

  @Override
  @Transactional
  public String deleteCandidateById(Long id) throws Exception {
    if (candidateRepository.existsById(id)) {
      resumeRepository.deleteAllByCandidateId(id);
      candidateRepository.deleteById(id);
      return "Delete successfully";
    } else {
      throw new Exception("Entity with that ID does not exist.");
    }
  }

  @Override
  public Page<Candidate> search(SearchCandidateDTO searchCandidateDTO) {
    return candidateRepository.findAll(CandidateSpecification.criteria(searchCandidateDTO),
        searchCandidateDTO.getPageable());
  }

  @Override
  public CandidateWithResumeDTO findByIdWithResume(Long id) {
    Candidate existCandidate = candidateRepository.findById(id).orElseThrow();
    List<Resume> resumes = resumeRepository.findAllByCandidateId(existCandidate.getId())
        .orElseThrow();
    CandidateWithResumeDTO result = new CandidateWithResumeDTO(existCandidate,resumes);
    return result;
  }
}
