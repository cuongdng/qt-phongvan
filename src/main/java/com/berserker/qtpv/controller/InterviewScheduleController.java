package com.berserker.qtpv.controller;

import com.berserker.qtpv.entity.InterviewSchedule;
import com.berserker.qtpv.model.interviewschedule.InterviewScheduleDTO;
import com.berserker.qtpv.model.interviewschedule.CreateInterviewScheduleDTO;
import com.berserker.qtpv.model.interviewschedule.UpdateInterviewScheduleDTO;
import com.berserker.qtpv.service.InterviewScheduleService;
import com.berserker.qtpv.service.impl.InterviewScheduleServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")
@RequiredArgsConstructor
public class InterviewScheduleController {
  private final InterviewScheduleService interviewScheduleService;

  @PostMapping("/create")
  ResponseEntity<InterviewSchedule> createSchedule(@RequestBody CreateInterviewScheduleDTO createInterviewScheduleDTO) {
    InterviewSchedule createdSchedule = this.interviewScheduleService.create(createInterviewScheduleDTO);
    return ResponseEntity.ok(createdSchedule);
  }


  @PutMapping("/{id}")
  ResponseEntity<InterviewSchedule> updateSchedule(@PathVariable Long id, @RequestBody UpdateInterviewScheduleDTO updateInterviewScheduleDTO) {
    InterviewSchedule updatedSchedule = this.interviewScheduleService.update(id, updateInterviewScheduleDTO);
    return ResponseEntity.ok(updatedSchedule);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteSchedule(@PathVariable Long id) throws Exception {
    String result = interviewScheduleService.delete(id);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/by-month")
  ResponseEntity<List<InterviewScheduleDTO>> findAllByMonth(@RequestParam int year, @RequestParam int month) {
    List<InterviewScheduleDTO> schedulesByMonth = interviewScheduleService.getScheduleByMonth(year, month);
    return ResponseEntity.ok(schedulesByMonth);
  }
}
