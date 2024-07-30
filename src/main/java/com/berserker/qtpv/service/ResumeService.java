package com.berserker.qtpv.service;

import com.berserker.qtpv.base.ResumeStatus;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.resume.SearchResumeDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

public interface ResumeService {
  Page<Resume> search(SearchResumeDTO searchResumeDTO);
  Resume changeStatus(Long id, ResumeStatus status) throws BadRequestException;
}
