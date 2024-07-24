package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
  Optional<Candidate> findByPhoneNumber(String phoneNumber);
}
