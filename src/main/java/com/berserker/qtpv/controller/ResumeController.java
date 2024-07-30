package com.berserker.qtpv.controller;

import com.berserker.qtpv.entity.Resume;
import com.berserker.qtpv.model.resume.SearchResumeDTO;
import com.berserker.qtpv.model.resume.UpdateStatusDTO;
import com.berserker.qtpv.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resume")
@RequiredArgsConstructor
public class ResumeController {
  private final ResumeService resumeService;
  @GetMapping("/search")
  ResponseEntity<Page<Resume>> search(SearchResumeDTO searchResumeDTO) throws Exception {
    try {
      return ResponseEntity.ok(resumeService.search(searchResumeDTO));
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
  @PostMapping("/change-status")
  ResponseEntity<Resume> updateStatus(@RequestBody UpdateStatusDTO updateStatusDTO)
      throws BadRequestException {
    return ResponseEntity.ok(resumeService.changeStatus(updateStatusDTO.getId(), updateStatusDTO.getStatus()));
  }
}
