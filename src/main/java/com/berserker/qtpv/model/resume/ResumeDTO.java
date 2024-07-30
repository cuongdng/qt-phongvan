package com.berserker.qtpv.model.resume;

import com.berserker.qtpv.base.ResumeStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeDTO {
  private Long id;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
  private Long candidateId;
  private String resumeUrl;
  private ResumeStatus status;
}
