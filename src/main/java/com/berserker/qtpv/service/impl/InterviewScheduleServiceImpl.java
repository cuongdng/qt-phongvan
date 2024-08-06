package com.berserker.qtpv.service.impl;

import com.berserker.qtpv.entity.Employee;
import com.berserker.qtpv.entity.InterviewProcess;
import com.berserker.qtpv.entity.InterviewSchedule;
import com.berserker.qtpv.model.interviewschedule.CreateInterviewScheduleDTO;
import com.berserker.qtpv.model.interviewschedule.InterviewScheduleDTO;
import com.berserker.qtpv.model.interviewschedule.UpdateInterviewScheduleDTO;
import com.berserker.qtpv.repository.EmployeeRepository;
import com.berserker.qtpv.repository.InterviewProcessRepository;
import com.berserker.qtpv.repository.InterviewScheduleRepository;
import com.berserker.qtpv.service.InterviewScheduleService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewScheduleServiceImpl implements InterviewScheduleService {

  private final InterviewScheduleRepository interviewScheduleRepository;
  private final InterviewProcessRepository interviewProcessRepository;
  private final EmployeeRepository employeeRepository;

  @Override
  @Transactional
  public InterviewSchedule create(CreateInterviewScheduleDTO createInterviewScheduleDTO) {
    if (interviewScheduleRepository.existsByTime(createInterviewScheduleDTO.getStartTime(),
        createInterviewScheduleDTO.getEndTime(), null)) {
      throw new IllegalArgumentException("There is already an interview process happen between "
          + createInterviewScheduleDTO.getStartTime() + " and "
          + createInterviewScheduleDTO.getEndTime());
    }
    InterviewProcess interviewProcess = interviewProcessRepository.findById(
        createInterviewScheduleDTO.getInterviewProcessId()).orElseThrow();
    List<Employee> interviewers = employeeRepository.findAllByIdIsIn(
        createInterviewScheduleDTO.getInterviewerIds());
    if (interviewers.size() != createInterviewScheduleDTO.getInterviewerIds().size()) {
      throw new IllegalArgumentException("One or more interviewers does not exist");
    }
    InterviewSchedule interviewSchedule = new InterviewSchedule(
        createInterviewScheduleDTO.getStartTime(), createInterviewScheduleDTO.getEndTime(),
        interviewProcess, interviewers);
    return interviewScheduleRepository.save(interviewSchedule);
  }

  @Override
  public InterviewSchedule update(Long scheduleId,
      UpdateInterviewScheduleDTO updateInterviewScheduleDTO) {
    if (interviewScheduleRepository.existsByTime(updateInterviewScheduleDTO.getStartTime(),
        updateInterviewScheduleDTO.getEndTime(), scheduleId)) {
      throw new IllegalArgumentException("There is already an interview process happen between "
          + updateInterviewScheduleDTO.getStartTime() + " and "
          + updateInterviewScheduleDTO.getEndTime());
    }
    InterviewSchedule existSchedule = interviewScheduleRepository.findById(scheduleId)
        .orElseThrow();
    List<Employee> interviewers = employeeRepository.findAllByIdIsIn(
        updateInterviewScheduleDTO.getInterviewerIds());
    if (interviewers.size() != updateInterviewScheduleDTO.getInterviewerIds().size()) {
      throw new IllegalArgumentException("One or more interviewers does not exist");
    }
    existSchedule.setStartTime(updateInterviewScheduleDTO.getStartTime());
    existSchedule.setEndTime(updateInterviewScheduleDTO.getEndTime());
    existSchedule.setInterviewers(interviewers);
    return interviewScheduleRepository.save(existSchedule);
  }


  @Override
  public List<InterviewScheduleDTO> getScheduleByMonth(int year, int month) {
    LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
    LocalDateTime endOfMonth = startOfMonth.withDayOfMonth(
        startOfMonth.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
    List<InterviewSchedule> schedules = interviewScheduleRepository.findByStartTimeBetween(
        startOfMonth, endOfMonth);
    return schedules.stream().map(this::convertScheduleToDTO).toList();
  }

  @Override
  public String delete(Long id) throws Exception {
    if (interviewScheduleRepository.existsById(id)) {
      interviewScheduleRepository.deleteById(id);
      return "Delete successfully";
    } else {
      throw new Exception("Entity with that ID does not exist.");
    }
  }

  InterviewScheduleDTO convertScheduleToDTO(InterviewSchedule schedule) {
    List<String> listInterviewerName = schedule.getInterviewers().stream().map(
        Employee::getFullName).toList();
    return InterviewScheduleDTO.builder().id(schedule.getId()).startTime(schedule.getStartTime())
        .endTime(schedule.getEndTime())
        .intervieweeName(schedule.getInterviewProcess().getResume().getCandidate().getFullName())
        .interviewersName(listInterviewerName).build();
  }
}
