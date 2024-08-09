package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.InterviewSchedule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Long>,
    JpaSpecificationExecutor<InterviewProcess> {
  @EntityGraph(attributePaths = {"interviewProcess.resume", "interviewers"})
  List<InterviewSchedule> findByStartTimeBetween(LocalDateTime startOfMonth, LocalDateTime endOfMonth);

  @Query(value = "select count(schedule) > 0 from InterviewSchedule schedule where ((:startTime > schedule.startTime  and :startTime < schedule.endTime) or (:endTime < schedule.endTime and :endTime > schedule.startTime) or (:startTime = schedule.startTime or :endTime = schedule.endTime)) and (:id is null or schedule.id != :id)")
  boolean existsByTime(LocalDateTime startTime, LocalDateTime endTime, Long id);

  void deleteByInterviewProcess_Id(Long processId);

  InterviewSchedule findByInterviewProcess_Id(Long processId);
}
