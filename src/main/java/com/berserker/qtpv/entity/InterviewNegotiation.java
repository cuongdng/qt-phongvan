package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class InterviewNegotiation extends BaseEntity {
  Long interviewProcessId;
  Integer offeredSalary;
  Integer expectedSalary;
}

