package com.berserker.qtpv.service;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.InterviewSchedule;
import com.berserker.qtpv.model.interviewprocess.ChangeProcessStatusDTO;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

public interface InterviewProcessService {
  void createProcess(CreateInterviewProcessDTO createInterviewProcessDTO);
  Page<InterviewProcess> search(SearchInterviewProcessDTO searchInterviewProcessDTO);
  InterviewProcess changeStatus(ChangeProcessStatusDTO changeProcessStatusDTO)
      throws BadRequestException;
}
