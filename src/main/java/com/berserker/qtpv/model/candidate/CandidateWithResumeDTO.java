package com.berserker.qtpv.model.candidate;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.entity.Resume;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CandidateWithResumeDTO {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String fullName;
  private String phoneNumber;
  private String email;
  private LocalDate dob;
  private List<ResumeDTO> resumes;

  public CandidateWithResumeDTO(Candidate candidate, List<Resume> resumes) {
    this.id = candidate.getId();
    this.createdAt = candidate.getCreatedAt();
    this.updatedAt = candidate.getUpdatedAt();
    this.fullName = candidate.getFullName();
    this.phoneNumber = candidate.getPhoneNumber();
    this.email = candidate.getEmail();
    this.dob = candidate.getDateOfBirth();
    this.resumes = resumes.stream().map(
        (resume -> ResumeDTO.builder().candidateId(resume.getCandidate().getId())
            .resumeUrl(resume.getResumeUrl()).id(
                resume.getId()).createdAt(resume.getCreatedAt()).updatedAt(resume.getUpdatedAt())
            .build())).collect(Collectors.toList());
  }
}
