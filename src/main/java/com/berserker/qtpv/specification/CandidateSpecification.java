package com.berserker.qtpv.specification;

import com.berserker.qtpv.entity.Candidate;
import com.berserker.qtpv.model.candidate.SearchCandidateDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class CandidateSpecification {
  public static Specification<Candidate> criteria(SearchCandidateDTO searchCandidateDTO) {
    return new Specification<Candidate>() {
      @Override
      public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> query,
          CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (searchCandidateDTO.getEmail() != null && !searchCandidateDTO.getEmail().isEmpty()){
          Predicate predicate = criteriaBuilder.equal(root.get("email"), searchCandidateDTO.getEmail());
          predicates.add(predicate);
        }
        if (searchCandidateDTO.getPhoneNumber() != null && !searchCandidateDTO.getPhoneNumber().isEmpty()) {
          Predicate predicate = criteriaBuilder.equal(root.get("phoneNumber"), searchCandidateDTO.getPhoneNumber());
          predicates.add(predicate);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
      }
    };
  }
}
