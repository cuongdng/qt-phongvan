package com.berserker.qtpv.model.interviewprocess;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInterviewFeedbackDTO {
  private Long processId;
  private Long interviewerId;
  private String content;
}
