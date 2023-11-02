package com.ahlesunnat.asws.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.AttendanceStatus;
import com.ahlesunnat.asws.domain.Attendence;
import com.ahlesunnat.asws.repository.AttendenceRepository;
import com.ahlesunnat.asws.service.AttendenceService;


@Service
public class AttendenceServiceImpl implements AttendenceService {

    @Autowired
    private AttendenceRepository attendanceRepository;


    @Override
    public List<Attendence> getAllAttendancesByStudentId(Long studentId) {
        return attendanceRepository.findAllByStudentId(studentId);
    }

    @Override
    public List<Attendence> getAttendancesByDate(LocalDate attendanceDate) {
        return attendanceRepository.findAllByAttendanceDate(attendanceDate);
    }

    @Override
    public Map<String, Object> analyzeAttendance(Long studentId, LocalDate startDate, LocalDate endDate) {
        List<Attendence> attendanceList = attendanceRepository.findAllByStudentIdAndAttendanceDateBetween(studentId, startDate, endDate);

        int totalDaysInRange = (int) ChronoUnit.DAYS.between(startDate, endDate);
        int presentDays = 0;
        int absentDays = 0;
        int lateDays = 0;

        for (Attendence attendance : attendanceList) {
            if (attendance.getStatus() == AttendanceStatus.PRESENT) {
                presentDays++;
            } else if (attendance.getStatus() == AttendanceStatus.ABSENT) {
                absentDays++;
            } else if (attendance.getStatus() == AttendanceStatus.LEAVE) {
                lateDays++;
            }
        }

        double attendancePercentage = (double) presentDays / totalDaysInRange * 100;

        Map<String, Object> analysisResult = new HashMap<>();
        analysisResult.put("totalDaysInRange", totalDaysInRange);
        analysisResult.put("presentDays", presentDays);
        analysisResult.put("absentDays", absentDays);
        analysisResult.put("leaveDays", lateDays);
        analysisResult.put("attendancePercentage", attendancePercentage);

        return analysisResult;
    }

    @Override
    public List<Attendence> createAttendance(List<Attendence> attendance) {
        
        for(Attendence a: attendance){
            Attendence ad = attendanceRepository.save(a);
        }
        return attendance;
    }

    public Map<String, Object> getAttendanceSummary(LocalDate startDate) {
        Map<String, Object> summary = new HashMap<>();

        // Fetch attendance records from the given start date to the present date
        LocalDate currentDate = LocalDate.now();
        List<Attendence> attendanceRecords = attendanceRepository.findByAttendanceDateBetween(startDate, currentDate);

        // Calculate totals for each status
        int presentCount = 0;
        int absentCount = 0;
        int leaveCount = 0;

        for (Attendence attendance : attendanceRecords) {
            switch (attendance.getStatus()) {
                case PRESENT:
                    presentCount++;
                    break;
                case ABSENT:
                    absentCount++;
                    break;
                case LEAVE:
                    leaveCount++;
                    break;
                // Add more cases if you have additional status types
            }
        }

        // Calculate the total number of students (assuming each student is unique)
        int totalStudents = attendanceRecords.stream()
                .map(Attendence::getStudent)
                .distinct()
                .mapToInt(student -> 1)
                .sum();

        summary.put("startDate", startDate);
        summary.put("endDate", currentDate);
        summary.put("presentCount", presentCount);
        summary.put("absentCount", absentCount);
        summary.put("leaveCount", leaveCount);
        summary.put("totalStudents", totalStudents);

        return summary;
    }
}
