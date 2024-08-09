package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.InterviewFeedback;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewFeedbackRepository extends JpaRepository<InterviewFeedback, Long> {
  Optional<InterviewFeedback> findByInterviewProcessIdAndInterviewerId(Long processId, Long interviewerId);
  List<InterviewFeedback> findAllByInterviewProcessId(Long processId);
}
