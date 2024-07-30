package com.berserker.qtpv.service;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.model.interviewprocess.CreateInterviewProcessDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import org.springframework.data.domain.Page;

public interface InterviewProcessService {
  void createProcess(CreateInterviewProcessDTO createInterviewProcessDTO);
  Page<InterviewProcess> search(SearchInterviewProcessDTO searchInterviewProcessDTO);

}
