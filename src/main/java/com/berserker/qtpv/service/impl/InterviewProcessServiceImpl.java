package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.base.InterviewProcessStatus;
import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.interviewprocess.ChangeProcessStatusDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import com.berserker.qtpv.repository.InterviewProcessRepository;
import com.berserker.qtpv.repository.InterviewScheduleRepository;
import com.berserker.qtpv.repository.ResumeRepository;
import com.berserker.qtpv.service.InterviewProcessService;
import com.berserker.qtpv.specification.InterviewProcessSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewProcessServiceImpl implements InterviewProcessService {
  private final InterviewProcessRepository interviewProcessRepository;
  private final ResumeRepository resumeRepository;
  private final InterviewScheduleRepository interviewScheduleRepository;

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

  @Override
  @Transactional
  public InterviewProcess changeStatus(ChangeProcessStatusDTO changeProcessStatusDTO)
      throws BadRequestException {
    InterviewProcess existedProcess = interviewProcessRepository.findById(changeProcessStatusDTO.getProcessId()).orElseThrow();
    if (existedProcess.getStatus().equals(InterviewProcessStatus.CANCEL) || existedProcess.getStatus().equals(InterviewProcessStatus.CANDIDATE_CANCEL)) {
      throw new BadRequestException("This process has been canceled. You can not change it status");
    }
    if (changeProcessStatusDTO.getProcessStatus().equals(InterviewProcessStatus.CANCEL) || changeProcessStatusDTO.getProcessStatus().equals(InterviewProcessStatus.CANDIDATE_CANCEL)) {
      interviewScheduleRepository.deleteByInterviewProcess_Id(existedProcess.getId());
    }
    existedProcess.setStatus(changeProcessStatusDTO.getProcessStatus());
    return existedProcess;
  }
}
