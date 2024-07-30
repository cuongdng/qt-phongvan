package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import com.berserker.qtpv.base.ResumeStatus;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume extends BaseEntity {
  @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;
  @Column(nullable = false)
  private String resumeUrl;
  private ResumeStatus status = ResumeStatus.IDLE;

  @JsonGetter("candidate")
  public Long getCandidateId() {
    return candidate.getId();
  }
}
