package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
  Optional<Candidate> findByPhoneNumber(String phoneNumber);
}
