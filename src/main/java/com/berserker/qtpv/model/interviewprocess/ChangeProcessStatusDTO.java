package com.berserker.qtpv.model.interviewprocess;

import com.berserker.qtpv.base.InterviewProcessStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeProcessStatusDTO {
  private Long processId;
  private InterviewProcessStatus processStatus;
}
