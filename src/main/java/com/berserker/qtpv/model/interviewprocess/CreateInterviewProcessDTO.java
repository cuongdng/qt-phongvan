package com.berserker.qtpv.model.interviewprocess;

import com.berserker.qtpv.base.InterviewProcessStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInterviewProcessDTO {
  private Long resumeId;
  private InterviewProcessStatus interviewProcessStatus;
}
