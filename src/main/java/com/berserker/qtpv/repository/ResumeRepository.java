package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
  Resume findByCandidateId(Long candidateId);
}
