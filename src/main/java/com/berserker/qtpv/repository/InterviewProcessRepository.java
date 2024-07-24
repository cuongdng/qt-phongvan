package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewProcessRepository extends JpaRepository<InterviewProcess, Long> {
}
