package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Employee extends BaseEntity {
  private String fullName;
}
