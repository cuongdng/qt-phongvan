package com.berserker.qtpv.model.resume;

import com.berserker.qtpv.base.ResumeStatus;
import com.berserker.qtpv.model.BasePaginationFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResumeDTO extends BasePaginationFilter {
  private Long candidateId;
  private ResumeStatus status;
}
