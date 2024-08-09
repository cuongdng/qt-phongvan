package com.berserker.qtpv.service;

import com.berserker.qtpv.entity.InterviewNegotiation;
import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.model.interviewprocess.ChangeProcessStatusDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewNegotiationDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewFeedbackDTO;
import com.berserker.qtpv.model.interviewprocess.InterviewFeedbackDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

public interface InterviewProcessService {
  void createProcess(CreateInterviewProcessDTO createInterviewProcessDTO);
  Page<InterviewProcess> search(SearchInterviewProcessDTO searchInterviewProcessDTO);
  InterviewProcess changeStatus(ChangeProcessStatusDTO changeProcessStatusDTO)
      throws BadRequestException;

  String createInterviewFeedback(CreateInterviewFeedbackDTO createInterviewFeedbackDTO)
      throws BadRequestException;

  List<InterviewFeedbackDTO> getInterviewFeedbackByProcessId(Long processId);

  String createInterviewNegotiation(CreateInterviewNegotiationDTO createInterviewNegotiationDTO)
      throws BadRequestException;

  InterviewNegotiation getInterviewNegotiation(Long processId);
}
