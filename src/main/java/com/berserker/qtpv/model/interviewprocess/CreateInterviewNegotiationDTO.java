package com.berserker.qtpv.model.interviewprocess;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInterviewNegotiationDTO {
  @NotNull(message = "must have value")
  private Long processId;
  @NotNull(message = "must have value")
  private Integer offerSalary;
  @NotNull(message = "must have value")
  private Integer expectedSalary;
}
