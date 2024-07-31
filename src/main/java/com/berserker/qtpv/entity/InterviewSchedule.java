package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class InterviewSchedule extends BaseEntity {
  LocalDateTime startTime;
  LocalDateTime endTime;

  @OneToOne()
  @JoinColumn(name = "interview_process_id")
  InterviewProcess interviewProcess;
}
