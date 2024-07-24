package com.berserker.qtpv.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateCandidateDTO {
  private String fullName;
  private String phoneNumber;
  private LocalDate dob;
  private String resumeUrl;
}
