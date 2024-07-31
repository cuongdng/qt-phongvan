package com.berserker.qtpv.model.resume;

import com.berserker.qtpv.base.ResumeStatus;
import com.berserker.qtpv.model.BasePaginationFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class SearchResumeDTO extends BasePaginationFilter {
  private Long candidateId;
  private ResumeStatus status;
}
