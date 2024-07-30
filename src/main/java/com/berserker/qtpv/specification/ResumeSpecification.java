package com.berserker.qtpv.specification;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.resume.SearchResumeDTO;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class ResumeSpecification {
  public static Specification<Resume> criteria(SearchResumeDTO searchResumeDTO) {
    return (Specification<Resume>) (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (searchResumeDTO.getCandidateId() != null){
        Predicate predicate = criteriaBuilder.equal(root.get("candidate").get("id"), searchResumeDTO.getCandidateId());
        predicates.add(predicate);
      }
      if (searchResumeDTO.getStatus() != null) {
        Predicate predicate = criteriaBuilder.equal(root.get("status"), searchResumeDTO.getStatus());
        predicates.add(predicate);
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}
