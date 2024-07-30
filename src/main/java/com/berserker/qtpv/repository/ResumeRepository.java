package com.berserker.qtpv.repository;

import com.berserker.qtpv.entity.Resume;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>,
    JpaSpecificationExecutor<Resume> {
  @Query("SELECT r FROM Resume r WHERE r.candidate.id = :candidateId")
  Optional<List<Resume>> findAllByCandidateId(Long candidateId);

  @Modifying
  @Query("DELETE FROM Resume r WHERE r.candidate.id = :candidateId")
  void deleteAllByCandidateId(Long candidateId);
}
