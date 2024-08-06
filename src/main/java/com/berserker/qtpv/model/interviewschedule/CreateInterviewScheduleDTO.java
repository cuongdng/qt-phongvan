package com.berserker.qtpv.model.interviewschedule;

import com.berserker.qtpv.base.InterviewProcessStatus;
import com.berserker.qtpv.entity.Employee;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInterviewScheduleDTO {
  private Long interviewProcessId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private List<Long> interviewerIds;
}

