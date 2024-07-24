package com.berserker.qtpv.entity;

import com.berserker.qtpv.base.BaseEntity;
import com.berserker.qtpv.model.CreateCandidateDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private LocalDate dateOfBirth;

  @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Resume> resumes;

  public Candidate(@NonNull CreateCandidateDTO dto) {
    this.fullName = dto.getFullName();
    this.phoneNumber = dto.getPhoneNumber();
    this.dateOfBirth = dto.getDob();
  }
}
