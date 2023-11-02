package com.ahlesunnat.asws.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.ahlesunnat.asws.domain.Attendence;

public interface AttendenceService {
    List<Attendence> createAttendance(List<Attendence> attendance);

    List<Attendence> getAllAttendancesByStudentId(Long studentId);

    List<Attendence> getAttendancesByDate(LocalDate attendanceDate);

    Map<String, Object> analyzeAttendance(Long studentId, LocalDate startDate, LocalDate endDate);
}
