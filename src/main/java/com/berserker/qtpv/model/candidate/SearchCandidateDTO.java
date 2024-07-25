package com.berserker.qtpv.model.candidate;

import com.berserker.qtpv.model.BasePaginationFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCandidateDTO extends BasePaginationFilter {
  private String phoneNumber;
  private String email;
}
