package com.berserker.qtpv.model.resume;

import com.berserker.qtpv.base.ResumeStatus;
import lombok.Data;

@Data
public class UpdateStatusDTO {
  private Long resumeId;
  private ResumeStatus resumeStatus;
}
