package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.InterviewProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewProcessRepository extends JpaRepository<InterviewProcess, Long>,
    JpaSpecificationExecutor<InterviewProcess> {

  @Override
  @EntityGraph(attributePaths = {"resume", "resume.candidate"})
  Page<InterviewProcess> findAll(Specification<InterviewProcess> spec, Pageable pageable);
}
