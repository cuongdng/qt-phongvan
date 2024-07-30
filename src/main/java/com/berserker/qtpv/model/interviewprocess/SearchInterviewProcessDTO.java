package com.berserker.qtpv.model.interviewprocess;

import com.berserker.qtpv.base.InterviewProcessStatus;
import com.berserker.qtpv.base.ResumeStatus;
import com.berserker.qtpv.model.BasePaginationFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInterviewProcessDTO extends BasePaginationFilter {
  private Long candidateId;
  private InterviewProcessStatus status;
}
