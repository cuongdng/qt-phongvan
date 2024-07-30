package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import com.berserker.qtpv.base.InterviewProcessStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class InterviewProcess extends BaseEntity {
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "resume_id")
  private Resume resume;
  private InterviewProcessStatus status;
}
