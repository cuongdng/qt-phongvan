package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>, JpaSpecificationExecutor<Candidate> {
  Optional<Candidate> findByEmail(String phoneNumber);
  @Query(value = "SELECT c, r FROM Candidate c JOIN Resume r ON r.candidate.id = c.id WHERE c.id = :id")
  Optional<Candidate> findDetailById(Long id);
}
