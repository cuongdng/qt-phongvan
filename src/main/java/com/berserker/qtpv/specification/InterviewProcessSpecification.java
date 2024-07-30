package com.berserker.qtpv.specification;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import com.berserker.qtpv.model.resume.SearchResumeDTO;
import com.berserker.qtpv.repository.InterviewProcessRepository;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class InterviewProcessSpecification {
  public static Specification<InterviewProcess> criteria(SearchInterviewProcessDTO searchInterviewProcessDTO) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (searchInterviewProcessDTO.getCandidateId() != null){
        Predicate predicate = criteriaBuilder.equal(root.get("resume").get("candidate").get("id"), searchInterviewProcessDTO.getCandidateId());
        predicates.add(predicate);
      }
      if (searchInterviewProcessDTO.getStatus() != null) {
        Predicate predicate = criteriaBuilder.equal(root.get("status"), searchInterviewProcessDTO.getStatus());
        predicates.add(predicate);
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}
