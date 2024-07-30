package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.base.InterviewProcessStatus;
import com.berserker.qtpv.base.ResumeStatus;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.resume.SearchResumeDTO;
import com.berserker.qtpv.repository.ResumeRepository;
import com.berserker.qtpv.service.InterviewProcessService;
import com.berserker.qtpv.service.ResumeService;
import com.berserker.qtpv.specification.ResumeSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

  private final ResumeRepository resumeRepository;
  private final InterviewProcessService interviewProcessService;

  @Override
  public Page<Resume> search(SearchResumeDTO searchResumeDTO) {
    return resumeRepository.findAll(ResumeSpecification.criteria(searchResumeDTO),
        searchResumeDTO.getPageable());
  }

  @Override
  @Transactional
  public Resume changeStatus(Long id, ResumeStatus status) throws BadRequestException {
    Resume existResume = resumeRepository.findById(id).orElseThrow();
    if (existResume.getStatus() == ResumeStatus.ACCEPTED) {
      throw new BadRequestException("Resume has already been accepted.");
    }
    existResume.setStatus(status);
    if (status == ResumeStatus.ACCEPTED) {
      interviewProcessService.createProcess(CreateInterviewProcessDTO.builder().interviewProcessStatus(
          InterviewProcessStatus.WAITING_INTERVIEW).resumeId(id).build());
    }
    return resumeRepository.save(existResume);
  }
}
