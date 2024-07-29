package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import com.berserker.qtpv.model.candidate.CreateCandidateDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends BaseEntity {

  @Column(nullable = false)
  private String fullName;
  @Column(nullable = false)
  private String phoneNumber;

  @Column(nullable = false, unique = true)
  private String email;
  private LocalDate dateOfBirth;
  public Candidate(@NonNull CreateCandidateDTO dto) {
    this.fullName = dto.getFullName();
    this.phoneNumber = dto.getPhoneNumber();
    this.dateOfBirth = dto.getDob();
    this.email = dto.getEmail();
  }
}
