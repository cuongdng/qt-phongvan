package com.berserker.qtpv.controller;

import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.interviewprocess.ChangeProcessStatusDTO;
import com.berserker.qtpv.model.interviewprocess.SearchInterviewProcessDTO;
import com.berserker.qtpv.model.resume.SearchResumeDTO;
import com.berserker.qtpv.model.resume.UpdateStatusDTO;
import com.berserker.qtpv.service.InterviewProcessService;
import com.berserker.qtpv.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("interview-process")
@RequiredArgsConstructor
public class InterviewProcessController {
  private final InterviewProcessService interviewProcessService;
  @GetMapping("/search")
  ResponseEntity<Page<InterviewProcess>> search(SearchInterviewProcessDTO searchInterviewProcessDTO) throws Exception {
    try {
      return ResponseEntity.ok(interviewProcessService.search(searchInterviewProcessDTO));
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @PutMapping("/change-status")
  ResponseEntity<InterviewProcess> changeStatus(@RequestBody ChangeProcessStatusDTO changeProcessStatusDTO)
      throws BadRequestException {
    return ResponseEntity.ok(interviewProcessService.changeStatus(changeProcessStatusDTO));

  }
}
