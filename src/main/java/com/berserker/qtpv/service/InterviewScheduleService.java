package com.berserker.qtpv.service;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.entity.InterviewSchedule;
import com.berserker.qtpv.model.candidate.CandidateWithResumeDTO;
import com.berserker.qtpv.model.candidate.CreateCandidateDTO;
import com.berserker.qtpv.model.candidate.SearchCandidateDTO;
import com.berserker.qtpv.model.interviewschedule.CreateInterviewScheduleDTO;
import com.berserker.qtpv.model.interviewschedule.InterviewScheduleDTO;
import com.berserker.qtpv.model.interviewschedule.UpdateInterviewScheduleDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface InterviewScheduleService {

  InterviewSchedule create(CreateInterviewScheduleDTO createInterviewScheduleDTO);

  InterviewSchedule update(Long scheduleId, UpdateInterviewScheduleDTO updateInterviewScheduleDTO);

  List<InterviewScheduleDTO> getScheduleByMonth(int year, int month);

  String delete(Long id) throws Exception;
}
