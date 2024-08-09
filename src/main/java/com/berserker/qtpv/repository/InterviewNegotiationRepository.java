package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.InterviewFeedback;
import com.berserker.qtpv.entity.InterviewNegotiation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewNegotiationRepository extends JpaRepository<InterviewNegotiation, Long> {
  Optional<InterviewNegotiation> findByInterviewProcessId(Long processId);
}