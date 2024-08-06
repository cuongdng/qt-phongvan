package com.berserker.qtpv.model.interviewschedule;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class UpdateInterviewScheduleDTO {
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private List<Long> interviewerIds;
}
