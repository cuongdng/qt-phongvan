package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import com.berserker.qtpv.repository.InterviewProcessRepository;
import com.berserker.qtpv.repository.ResumeRepository;
import com.berserker.qtpv.service.InterviewProcessService;
import com.berserker.qtpv.specification.InterviewProcessSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewProcessServiceImpl implements InterviewProcessService {
  private final InterviewProcessRepository interviewProcessRepository;
  private final ResumeRepository resumeRepository;

  @Override
  @Transactional
  public void createProcess(CreateInterviewProcessDTO createInterviewProcessDTO) {
    Resume existResume = resumeRepository.findById(createInterviewProcessDTO.getResumeId()).orElseThrow();
    InterviewProcess interviewProcess = InterviewProcess.builder().resume(existResume).status(createInterviewProcessDTO.getInterviewProcessStatus()).build();
    interviewProcessRepository.save(interviewProcess);
  }
  @Override
  public Page<InterviewProcess> search(SearchInterviewProcessDTO searchInterviewProcessDTO) {
    return interviewProcessRepository.findAll(InterviewProcessSpecification.criteria(searchInterviewProcessDTO),
        searchInterviewProcessDTO.getPageable());
  }
}
