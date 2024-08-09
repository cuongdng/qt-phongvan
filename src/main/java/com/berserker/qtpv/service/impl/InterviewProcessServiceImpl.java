package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.base.InterviewProcessStatus;
import com.berserker.qtpv.entity.Employee;
import com.berserker.qtpv.entity.InterviewFeedback;
import com.berserker.qtpv.entity.InterviewNegotiation;
import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.interviewprocess.ChangeProcessStatusDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewNegotiationDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewFeedbackDTO;
import com.berserker.qtpv.model.interviewprocess.InterviewFeedbackDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import com.berserker.qtpv.repository.EmployeeRepository;
import com.berserker.qtpv.repository.InterviewFeedbackRepository;
import com.berserker.qtpv.repository.InterviewNegotiationRepository;
import com.berserker.qtpv.repository.InterviewProcessRepository;
import com.berserker.qtpv.repository.InterviewScheduleRepository;
import com.berserker.qtpv.repository.ResumeRepository;
import com.berserker.qtpv.service.InterviewProcessService;
import com.berserker.qtpv.specification.InterviewProcessSpecification;
import java.util.List;
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
  private final InterviewFeedbackRepository interviewFeedbackRepository;
  private final EmployeeRepository employeeRepository;
  private final InterviewNegotiationRepository interviewNegotiationRepository;

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
      if (changeProcessStatusDTO.getContent() == null && changeProcessStatusDTO.getContent().is) {

      }
      interviewScheduleRepository.deleteByInterviewProcess_Id(existedProcess.getId());
    }
    existedProcess.setStatus(changeProcessStatusDTO.getProcessStatus());
    return existedProcess;
  }

  @Override
  public String createInterviewFeedback(CreateInterviewFeedbackDTO createInterviewFeedbackDTO)
      throws BadRequestException {
    InterviewFeedback interviewFeedback = interviewFeedbackRepository.findByInterviewProcessIdAndInterviewerId(
        createInterviewFeedbackDTO.getProcessId(),
        createInterviewFeedbackDTO.getInterviewerId()).orElse(new InterviewFeedback());
    interviewFeedback.setFeedback(createInterviewFeedbackDTO.getContent());

    if (!interviewProcessRepository.existsById(createInterviewFeedbackDTO.getProcessId())) {
      throw new BadRequestException("That process does not exist");
    }
    interviewFeedback.setInterviewProcessId(createInterviewFeedbackDTO.getProcessId());

    if (!employeeRepository.existsById(createInterviewFeedbackDTO.getInterviewerId())) {
      throw new BadRequestException("That interviewer does not exist in database");
    }
    interviewFeedback.setInterviewerId(createInterviewFeedbackDTO.getInterviewerId());
    interviewFeedbackRepository.save(interviewFeedback);
    return "Successfully";
  }

  @Override
  public List<InterviewFeedbackDTO> getInterviewFeedbackByProcessId(Long processId) {
    List<InterviewFeedback> interviewFeedbacks = interviewFeedbackRepository.findAllByInterviewProcessId(processId);
    return interviewFeedbacks.stream().map(this::convertToInterviewFeedbackDTO).toList();
  }

  @Override
  public String createInterviewNegotiation(
      CreateInterviewNegotiationDTO createInterviewNegotiationDTO) throws BadRequestException {
    InterviewNegotiation negotiation = interviewNegotiationRepository.findByInterviewProcessId(
        createInterviewNegotiationDTO.getProcessId()).orElse(new InterviewNegotiation());

    if (!interviewProcessRepository.existsById(createInterviewNegotiationDTO.getProcessId())) {
      throw new BadRequestException("That process does not exist");
    }
    negotiation.setInterviewProcessId(createInterviewNegotiationDTO.getProcessId());

    negotiation.setExpectedSalary(createInterviewNegotiationDTO.getExpectedSalary());
    negotiation.setOfferedSalary(createInterviewNegotiationDTO.getOfferSalary());
    interviewNegotiationRepository.save(negotiation);
    return "Successfully";
  }

  @Override
  public InterviewNegotiation getInterviewNegotiation(Long processId) {
   return interviewNegotiationRepository.findByInterviewProcessId(processId).orElseThrow();
  }

  private InterviewFeedbackDTO convertToInterviewFeedbackDTO(InterviewFeedback interviewFeedback) {
    Employee interviewer = employeeRepository.findById(interviewFeedback.getInterviewerId()).orElseThrow();
    return new InterviewFeedbackDTO(interviewFeedback.getInterviewerId(), interviewer.getFullName(), interviewFeedback.getFeedback(), interviewFeedback.getUpdatedAt());
  }
}
