package com.berserker.qtpv.model.candidate;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateCandidateDTO {
  private String fullName;
  private String phoneNumber;
  private String email;
  private LocalDate dob;
  private String resumeUrl;
}
