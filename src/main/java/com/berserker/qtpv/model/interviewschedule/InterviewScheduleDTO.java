package com.berserker.qtpv.model.interviewschedule;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterviewScheduleDTO {
  private Long id;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String intervieweeName;
  private List<String> interviewersName;
}
