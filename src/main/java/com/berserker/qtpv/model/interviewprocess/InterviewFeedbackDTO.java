package com.berserker.qtpv.model.interviewprocess;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InterviewFeedbackDTO {
  private Long interviewerId;
  private String interviewerName;
  private String content;
  private LocalDateTime createdDate;
}
